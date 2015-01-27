package co.mobilemakers.sandwichshop;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    Button buttonPlaceOrder;
    CheckBox checkBoxHeartyItalian;
    CheckBox checkBoxJalapeno;
    CheckBox checkBoxCheddar;
    CheckBox checkBoxOregano;
    CheckBox checkBoxRoasted;
    CheckBox checkBoxMozarella;
    CheckBox checkBoxSpinach;
    CheckBox checkBoxTomatoes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareCheckBoxes();
        prepareButtonPlaceOrder();
    }

    private void prepareCheckBoxes(){
          checkBoxHeartyItalian=(CheckBox)findViewById(R.id.check_box_hearty_italian);
          checkBoxJalapeno=(CheckBox)findViewById(R.id.check_box_jalapeno_cheese);
          checkBoxCheddar=(CheckBox)findViewById(R.id.check_box_cheddar);
          checkBoxOregano=(CheckBox)findViewById(R.id.check_box_oregano);
          checkBoxRoasted=(CheckBox)findViewById(R.id.check_box_roasted_garlic);
          checkBoxMozarella=(CheckBox)findViewById(R.id.check_box_mozzarella);
          checkBoxSpinach=(CheckBox)findViewById(R.id.check_box_spinach);
          checkBoxTomatoes=(CheckBox)findViewById(R.id.check_box_tomatoes);
    }
    private void  prepareButtonPlaceOrder(){
        buttonPlaceOrder = (Button)findViewById(R.id.button_place_order);
        buttonPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String order="Confirm your order: \n";
                ArrayList<String> toppings =new ArrayList<String>();
                String toppingsOptions="";
                int selectedId = ((RadioGroup)findViewById(R.id.radio_group_bread)).getCheckedRadioButtonId();
                order+="A sanwich " +((RadioButton) findViewById(selectedId)).getText().toString().toUpperCase();

                if(checkBoxHeartyItalian.isChecked()){
                    toppings.add( checkBoxHeartyItalian.getText().toString().toUpperCase() );
                }
                if(checkBoxJalapeno.isChecked()){
                    toppings.add(  checkBoxJalapeno.getText().toString().toUpperCase() );
                }
                if(checkBoxCheddar.isChecked()){
                    toppings.add(  checkBoxCheddar.getText().toString().toUpperCase() );
                }
                if(checkBoxOregano.isChecked()){
                    toppings.add(  checkBoxOregano.getText().toString().toUpperCase() );
                }
                if(checkBoxRoasted.isChecked()){
                    toppings.add(  checkBoxRoasted.getText().toString().toUpperCase() );
                }
                if(checkBoxMozarella.isChecked()){
                    toppings.add(  checkBoxMozarella.getText().toString().toUpperCase() );
                }
                if(checkBoxSpinach.isChecked()){
                    toppings.add(  checkBoxSpinach.getText().toString().toUpperCase() );
                }
                if(checkBoxTomatoes.isChecked()){
                    toppings.add(  checkBoxTomatoes.getText().toString().toUpperCase());
                }

                if(!toppings.isEmpty()){
                    toppingsOptions+=" with ";
                    for(int i=0; i<toppings.size()-1;i++){
                        toppingsOptions+=toppings.get(i);
                        if(toppings.size()>2)toppingsOptions+=", ";
                    }
                    toppingsOptions+=" and "+toppings.get(toppings.size()-1);
                }

                if(!toppingsOptions.isEmpty()){
                    order+=toppingsOptions;
                }

                Intent intent= new Intent(MainActivity.this, ConfirmationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("order",order);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
