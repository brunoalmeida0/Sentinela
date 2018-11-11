package com.example.cybersecurity.hackaton.sentinela2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.cybersecurity.hackaton.sentinela2.Models.Mensagem;
import com.example.cybersecurity.hackaton.sentinela2.adapter.MensagemAdapter;
import com.example.cybersecurity.hackaton.sentinela2.data.RetrofitInit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;

    private ListView mMessageListView;
    private MensagemAdapter mMessageAdapter;
    private ProgressBar mProgressBar;
    private EditText mMessageEditText;
    private Button mSendButton;

    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mUsername = ANONYMOUS;

        mProgressBar = findViewById(R.id.progressBar);
        mMessageListView = findViewById(R.id.messageListView);
        mMessageEditText =  findViewById(R.id.messageEditText);
        mSendButton = findViewById(R.id.sendButton);

        List<Mensagem> friendlyMessages = new ArrayList<>();
        mMessageAdapter = new MensagemAdapter(this, R.layout.item_mensagem, friendlyMessages);
        mMessageListView.setAdapter(mMessageAdapter);

        mProgressBar.setVisibility(ProgressBar.INVISIBLE);

        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mensagem mensagem = new Mensagem();
                mensagem.setText(mMessageEditText.getText().toString());
                mensagem.setDescription("user");
                mMessageAdapter.add(mensagem);
                sendQuestion(mMessageEditText.getText().toString());
                mMessageEditText.setText("");
            }
        });

        callFirstMessage();
    }

    private void callFirstMessage(){
        Call<List<Mensagem>> mensagemCall = new RetrofitInit().getMensagemService().getMensagem();
        mensagemCall.enqueue(new Callback<List<Mensagem>>() {
            @Override
            public void onResponse(Call<List<Mensagem>> call, Response<List<Mensagem>> response) {
                Log.d(TAG,response.body().toString());
                Mensagem mensagem = new Mensagem();
                mensagem.setDescription("chat");
                mensagem.setText(response.body().get(0).getText());
                mMessageAdapter.add(mensagem);
            }
            @Override
            public void onFailure(Call<List<Mensagem>> call, Throwable t) {
                Log.d(TAG+"ERRO",t.getMessage());
            }
        });
    }

    private void sendQuestion(String mensagem){
        Call<List<Mensagem>> mensagemCall = new RetrofitInit().getMensagemService().savePost(mensagem);
        mensagemCall.enqueue(new Callback<List<Mensagem>>() {
            @Override
            public void onResponse(Call<List<Mensagem>> call, Response<List<Mensagem>> response) {
                Log.d(TAG+"Sucess", response.body().get(0).getText());
                Mensagem mensagem = new Mensagem();
                mensagem.setDescription("chat");
                mensagem.setText(response.body().get(0).getText());
                mMessageAdapter.add(mensagem);
            }
            @Override
            public void onFailure(Call<List<Mensagem>> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });

    }
}
