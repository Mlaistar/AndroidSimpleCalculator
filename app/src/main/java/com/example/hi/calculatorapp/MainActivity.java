package com.example.hi.calculatorapp;

import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    //Adapter adapter;
    Character[] signArray = new Character[] { '+', '-', '*', '/' };
    EditText editTextNumber1, editTextNumber2;
    Button btnCalculate;
    TextView textViewMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner)findViewById(R.id.spinner);
        btnCalculate = (Button)findViewById(R.id.btnCalculate);
        editTextNumber1 = (EditText)findViewById(R.id.editTextNumber1);
        editTextNumber2 = (EditText)findViewById(R.id.editTextNumber2);
        textViewMessage = (TextView)findViewById(R.id.textViewMessage);

        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this, android.R.layout.simple_spinner_item, signArray);
        ArrayAdapter<Character> adapter = new ArrayAdapter<Character>(MainActivity.this, android.R.layout.simple_spinner_item, signArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str1 = editTextNumber1.getText().toString();
                String str2 = editTextNumber2.getText().toString();

                //double ope1, ope2;
                double num1 = 0, num2 = 0;
                try{
                    num1 = Double.parseDouble(str1);
                    num2 = Double.parseDouble(str2);

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "You must input a number.", Toast.LENGTH_SHORT).show();
                    textViewMessage.setText("No!");
                }

//                double num1 = Double.valueOf(editTextNumber1.getText().toString());
//                double num2 = Double.valueOf(editTextNumber2.getText().toString());
                double sum = 0;

                switch((Character)spinner.getSelectedItem()){
                    case '+' :
                        sum = num1 + num2;
                        break;
                    case '/' :
                        if(num2 <= 0) {
                            Toast.makeText(MainActivity.this, "Please type a number greater than 0, Divide cannot be zero/0.", Toast.LENGTH_SHORT).show();
                            textViewMessage.setText("No!");
                            return;
                        }
                        else
                            sum = num1 / num2;
                        break;
                    case '-' :
                        sum = num1 - num2;
                        break;
                    case '*' :
                        sum = num1 * num2;
                        break;
                }
                textViewMessage.setText( num1 + " " + spinner.getSelectedItem() + " " + num2 + " = " + sum);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.itemAbout:

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("This is Mlamleli's calculator, my email is Mlaistarr@gmail.com");
                builder.setTitle("About");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create();
                break;

            case R.id.itemQuit:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
