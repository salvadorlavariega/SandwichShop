package co.mobilemakers.sandwichshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by root on 26/01/15.
 */
public class ConfirmationActivity extends Activity {

    Button confirmButton;
    Button cancelButton;
    TextView orderDescTaxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        Bundle bundle = this.getIntent().getExtras();
        String order = bundle.getString("order");
        prepareButtons();
        orderDescTaxtView = (TextView)findViewById(R.id.text_view_order_description);
        orderDescTaxtView.setText(order);

    }

    private void prepareButtons() {
        confirmButton=(Button)findViewById(R.id.button_confirm);
        cancelButton=(Button)findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmationActivity.this,SuccessFullyActivity.class);
                startActivity(intent);
            }
        });
    }
}
