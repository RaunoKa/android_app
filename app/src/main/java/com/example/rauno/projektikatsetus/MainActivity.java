package com.example.rauno.projektikatsetus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View.OnKeyListener;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText sisend = (EditText) findViewById(R.id.sisend);
        sisend.setVisibility(View.GONE);
        //Alustamise nupp:
        Button button = (Button)findViewById(R.id.nupp);
        button.setOnClickListener(
                new Button.OnClickListener() { //interface
                    public void onClick(View v) {
                        TextView text = (TextView) findViewById(R.id.tulemus);
                        TextView kys = (TextView) findViewById(R.id.küsimus);
                        EditText sisend = (EditText) findViewById(R.id.sisend);

                        sisend.setVisibility(View.VISIBLE);
                        text.setText("");
                        kys.setText("");

                        Alusta alusta = new Alusta();
                        Button button = (Button)findViewById(R.id.nupp);
                        button.setVisibility(View.GONE);
                        kys.setText(alusta.kys + " =");

                        addKeyListener(alusta);
                    }
                }
        );

    }

    public void addKeyListener(final Alusta alusta) {

        // get sisend_väli component
        final EditText sisend_väli;
        sisend_väli = (EditText) findViewById(R.id.sisend);

        // add a keylistener to keep track user input
        assert sisend_väli != null;

        sisend_väli.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                // if keydown and "ENTER" is pressed
                if ((event.getAction() == KeyEvent.ACTION_DOWN)
                        && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    // display a floating message
                    Editable sisend = sisend_väli.getText();
                    if (sisend.toString().equals(""))
                        return false;
                    String tulemus = alusta.kontrolli(sisend.toString());

                    TextView tulemuste_kirje = (TextView) findViewById(R.id.tulemus);
                    TextView kys_kirje = (TextView) findViewById(R.id.küsimus);
                    Button button = (Button)findViewById(R.id.nupp);

                    //kui viimase eluga vastati valesti, katkesta kõik!
                    if (tulemus.startsWith("M")) {
                        button.setVisibility(View.VISIBLE);
                        sisend_väli.setVisibility(View.GONE);

                        kys_kirje.setText("Mäng läbi!");
                        tulemuste_kirje.setText(alusta.vormista_tulemus());
                        return true;
                    }
                    //tühjenda sisendikast
                    sisend_väli.setText("");

                    Toast.makeText(MainActivity.this,
                            "Sisestasid: " + sisend + " õige:" + Double.toString(alusta.vast), Toast.LENGTH_LONG).show();
                    alusta.mängi();

                    kys_kirje.setText(alusta.kys + " =");
                    tulemuste_kirje.setText(tulemus);
                    return true;

                } else if ((event.getAction() == KeyEvent.ACTION_DOWN)
                        && (keyCode == KeyEvent.KEYCODE_MINUS)) {

                    // display a floating message
                    //Toast.makeText(MainActivity.this,
                    //        " - is pressed!", Toast.LENGTH_LONG).show();
                    //return true;
                }

                return false;
            }

            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
            }

        });
    }}