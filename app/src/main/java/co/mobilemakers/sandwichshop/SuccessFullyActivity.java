package co.mobilemakers.sandwichshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by root on 26/01/15.
 */
public class SuccessFullyActivity extends Activity{

    private Button okButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successfully);
        okButton = (Button)findViewById(R.id.button_ok);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccessFullyActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
