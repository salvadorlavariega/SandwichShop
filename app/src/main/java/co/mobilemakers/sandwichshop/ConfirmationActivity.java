package co.mobilemakers.sandwichshop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by root on 26/01/15.
 */
public class ConfirmationActivity extends ActionBarActivity {

    Button confirmButton;
    Button cancelButton;
    TextView orderDescTaxtView;
    ArrayList<Parcelable>  listParcelable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        Bundle bundle = this.getIntent().getExtras();

        listParcelable = bundle.getParcelableArrayList("parcelableList");
        prepareButtons();
        orderDescTaxtView = (TextView)findViewById(R.id.text_view_order_description);
        printOrders();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


    }

    private void printOrders() {
        for(int i=0; i<listParcelable.size(); i++){
            OrderBeanParcelable o =(OrderBeanParcelable) listParcelable.get(i);
            orderDescTaxtView.append(o.getNameSandwich() +"\n"+ o.getToppingsOptions()+"\n---------------");
        }
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
