package ca.roumani.rex1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    RexModel rexModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        rexModel = new RexModel();
        setContentView(R.layout.activity_main);
    }
    public void RegexButtonPressed (View v) {
        CheckBox lettersCheckBox = (CheckBox) findViewById(R.id.letter);
        CheckBox digitsCheckBox = (CheckBox) findViewById(R.id.digit);
        CheckBox anchorsCheckBox = (CheckBox) findViewById(R.id.anchor);

        rexModel.setLetter(lettersCheckBox.isChecked());
        rexModel.setDigit(digitsCheckBox.isChecked());
        rexModel.setAnchor(anchorsCheckBox.isChecked());

        rexModel.generate(2);
        TextView regexTextView = (TextView) findViewById(R.id.regexTextView);
        regexTextView.setText(rexModel.getRex());

    }
    public void checkStringButtonPressed (View v) {
        EditText inputStringEditText = (EditText) findViewById(R.id.stringEditText);
        String inputString = inputStringEditText.getText().toString();
        if (!(inputString.isEmpty() || inputString == null)) {
            TextView logTextView = (TextView) findViewById(R.id.log);
            logTextView.append(String.format("\nregex = %s, string = %s ----> %s", rexModel.getRex(), inputString, rexModel.doesMatch(inputString)));
        }
    }
}
