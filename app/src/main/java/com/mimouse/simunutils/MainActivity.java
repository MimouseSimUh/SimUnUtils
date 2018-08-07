package com.mimouse.simunutils;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mimouse.simunutilslib.base.BaseActivity;
import com.mimouse.simunutilslib.encryption.aes.MiAES;
import com.mimouse.simunutilslib.encryption.pbe.MiPBE;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class MainActivity extends BaseActivity {
    private TextView textView1;
    private TextView textView2;
    private Button button1;
    private Button button2;

    MiPBE miPBE = new MiPBE();
    MiAES miAES = new MiAES();
    private byte[] salt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
    }

    @Override
    public void initData() {
        salt = miPBE.getSalt();
    }

    @Override
    public void initListener() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*try {
                    textView2.setText(miPBE.encrypt(textView1.getText().toString(), "wangkang", salt));
                } catch (InvalidKeySpecException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchPaddingException | BadPaddingException | InvalidKeyException | IllegalBlockSizeException e) {
                    e.printStackTrace();
                }*/

                textView2.setText(miAES.doEncrypt(textView1.getText().toString(),"wangkang"));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = textView2.getText().toString();
                textView2.setText(miAES.doDecrypt(text,"wangkang"));

                /*try {
                    textView2.setText(miPBE.decrypt(text, "wangkang", salt));
                } catch (InvalidKeySpecException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchPaddingException | BadPaddingException | InvalidKeyException | IllegalBlockSizeException e) {
                    e.printStackTrace();
                }*/

            }
        });
    }
}
