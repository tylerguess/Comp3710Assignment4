package com.example.spendingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button addBttn, deleteBttn;
    EditText dateField, moneyField, reasonField, history;
    TextView balanceField;
    float currentBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBttn = findViewById(R.id.addButton);
        deleteBttn = findViewById(R.id.deleteButton);
        dateField = findViewById(R.id.dateField);
        moneyField = findViewById(R.id.moneyField);
        reasonField = findViewById(R.id.reasonField);
        history = findViewById(R.id.history);
        balanceField = findViewById(R.id.balance);
        currentBalance = 0;
        balanceField.setText("Current Balance: $" + currentBalance);

        SharedPreferences pref = getPreferences(0);
        String content = pref.getString("0", "");
        history.setText(content);
        currentBalance = pref.getFloat("1", 0);
        balanceField.setText("Current Balance: $" + currentBalance);


        addBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double moneyToAdd = Double.parseDouble(moneyField.getText().toString());
                String date = dateField.getText().toString();
                String reason = reasonField.getText().toString();
                String message = "\nAdded $" + moneyToAdd + " on " + date + " from " + reason;
                history.append(message);

                currentBalance += moneyToAdd;
                balanceField.setText("Current Balance: $" + currentBalance);

                saveInfo();

            }
        });

        deleteBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double moneyToSubtract = Double.parseDouble(moneyField.getText().toString());
                String date = dateField.getText().toString();
                String reason = reasonField.getText().toString();
                String message = "\nSpent $" + moneyToSubtract + " on " + date + " for " + reason;
                history.append(message);

                currentBalance -= moneyToSubtract;
                balanceField.setText("Current Balance: $" + currentBalance);

                saveInfo();

            }
        });

    }

    public void saveInfo() {
        SharedPreferences pref = getPreferences(0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("0", history.getText().toString());
        editor.putFloat("1", currentBalance);
        editor.commit();
    }
}
