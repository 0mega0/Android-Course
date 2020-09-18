package com.example.simplechat;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplechat.adapter.MessageAdapter;
import com.example.simplechat.model.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private List<Message> msgList=new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MessageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        EditText input_message = findViewById(R.id.et_message);
        Button btn_send = findViewById(R.id.btn_send);
        //testMsgs();
        inputText = findViewById(R.id.et_message);
        send = findViewById(R.id.btn_send);
        msgRecyclerView = findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);

        msgRecyclerView.addItemDecoration(new SpaceItemDecoration(40));
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MessageAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content=inputText.getText().toString();
                if(!"".equals(content)){
                    Message msg=new Message(content,Message.TYPE_SEND);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    Message msg2=new Message(content,Message.TYPE_RECIEVE);
                    msgList.add(msg2);
                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    inputText.setText("");
                }
            }
        });
    }
        public void testMsgs(){
            Message msg1=new Message("hello",Message.TYPE_RECIEVE);
            msgList.add(msg1);
            Message msg2=new Message("hello.who is that",Message.TYPE_SEND);
            msgList.add(msg2);

        }}



    class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace;

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.left = 10;
            outRect.right = 10;
            outRect.bottom =0;
            if (parent.getChildAdapterPosition(view) != 0) {
                outRect.top = mSpace;
            }
            else{
                outRect.top=0;
            }

        }

        public SpaceItemDecoration(int space) {
            this.mSpace = space;
        }
}
