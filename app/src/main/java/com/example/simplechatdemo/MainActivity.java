package com.example.simplechatdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplechatdemo.adapter.MsgAdapter;
import com.example.simplechatdemo.response.Msg;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Msg> msgList = new ArrayList<>();
    private EditText inputEditText;
    private Button sendButton;
    private RecyclerView msgRecycleView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsg();
        inputEditText = (EditText) findViewById(R.id.input_edit_text);
        sendButton = (Button) findViewById(R.id.send_button);
        msgRecycleView = (RecyclerView) findViewById(R.id.msg_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecycleView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecycleView.setAdapter(adapter);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputEditText.getText().toString();
                if(!content.equals("")) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    msg = new Msg(content, Msg.TYPE_RECEIVED);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);
                    msgRecycleView.scrollToPosition(msgList.size() - 1);
                    inputEditText.setText("");
                }
            }
        });
    }

    private void initMsg() {
        Msg msg;
        msg= new Msg("hello guy", Msg.TYPE_RECEIVED);
        msgList.add(msg);
        msg = new Msg("hi guy", Msg.TYPE_SENT);
        msgList.add(msg);
        msg = new Msg("This is Tom", Msg.TYPE_RECEIVED);
        msgList.add(msg);
    }
}