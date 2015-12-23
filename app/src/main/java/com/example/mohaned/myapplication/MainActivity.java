package com.example.mohaned.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private EditText wordTV;
    private EditText textCC;
    private EditText textCC2;
    private EditText textCC3;
    private EditText textCC4;
    private String text;
    public String[] code = new String[64];
    private String codest="";
    private String binaryst="";
    private String stout="";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.wordTV = (EditText) this.findViewById(R.id.wordTV);
        this.textCC = (EditText) this.findViewById(R.id.textb);
        this.textCC2 = (EditText) this.findViewById(R.id.textb2);
        this.textCC3 = (EditText) this.findViewById(R.id.textb3);
        this.textCC4 = (EditText) this.findViewById(R.id.textb4);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        for (int i = 0; i < 64; i++) {
            code[i] = "";
        }
        for (char ch = 'A'; ch <= 'Z'; ++ch) {
            //code[ch - 'A'] = "" + ch;
            setnewcode("" + ch);
        }
    }

    public void hasBottonPressed(View v) {
        String y = this.wordTV.getText().toString();
        String m = removespace(y);
        //int u = checkcode(m);
        m = m.toUpperCase();
        this.textCC.setText(m);
        text=m;
    }

    public void decode(View v)
    {
        String f="";
        for (int i=1; i<=binaryst.length(); i++)
        {
            //String g="";
            f=f+binaryst.charAt(i-1);
            if(i%6==0)
            {
                //int x = convertint(f);
                int x = Integer.parseInt(f, 2);
                stout=stout+code[x];
                f="";
            }

        }
        this.textCC4.setText(stout);

    }
    public int convertint(String code)
    {
        int k=0;
        int i=5;
        int j=1;
        int result=0;
        while (i>=0)
        {
            char c= code.charAt(i);
            result= result+j*(int)c;
            j=j*2;
            i--;

        }
        return result;
    }
    public void encode (View v)
    {
        int i=0;
        while (i<text.length())
        {
            int value= checkcode(""+text.charAt(i));
            if(i+1<text.length())
            {
                String j = text.substring(i, i + 2);
                //""+text.charAt(i)+text.charAt(i+1);
                int value2 = checkcode(j);
                if (value2 != 99)
                {
                    value = value2;
                    i++;
                }
                else
                {
                    setnewcode(j);
                }
            }
            else
            {
                value= checkcode(""+text.charAt(i));
            }
            String k=value+" ";
            codest = codest+k;
            i++;
        }
        this.textCC2.setText(codest);
    }
    public int checkcode(String cur)
    {
        int z=99;
        for(int i=0; i<64; i++)
        {
            if (code[i].equals(cur))
            {
                z= i;
            }

        }
        return z;
    }

    public void setnewcode(String a) {
        int i = 0;
        while (code[i] != "") {
            i++;
        }
        code[i] = a;
    }

    public void convertbinary(View v)
    {
        String num="";
        int num2=0;
        for (int i =0; i<codest.length(); i++)
        {
            if(codest.charAt(i) != ' ')
            {
                num=num+codest.charAt(i);
            }
            if(codest.charAt(i)==' ')
            {
                num2=Integer.parseInt(num);
                binaryc(num2);
                num="";
            }
        }
        this.textCC3.setText(removespace(binaryst+"..."+binaryst.length()+"bit..instead..of.."+ text.length()*8+"bit..in..ascii"));
    }
    public void binaryc(int n)
    {
        String binary = "";
        if(n==0)
        {
            binary="000000";
        }
        else
        {
            while (n > 0)
            {
                int rem = n % 2;
                binary = rem + binary;
                n = n / 2;
            }
        }
        if(binary.length()==5)
        {
            binary="0"+binary;
        }
        else if(binary.length()==4)
        {
            binary="00"+binary;
        }
        else if(binary.length()==3)
        {
            binary="000"+binary;
        }
        else if (binary.length()<3)
        {
            int i=0;
            while (i<(10-binary.length()))
            {
                binary="0"+binary;
                i++;
            }
        }
        binaryst=binaryst+binary;

    }
    public String removespace(String s) {
        String k = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                k = k + s.charAt(i);
            }
        }
        return k;
    }

    public void newscreen(View v) {
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
    }
}
