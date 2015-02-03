package co.mobilemakers.sandwichshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    private final String LOG_TAG=MainActivity.class.getSimpleName();
    Button buttonPlaceOrder;
    CheckBox checkBoxHeartyItalian;
    CheckBox checkBoxJalapeno;
    CheckBox checkBoxCheddar;
    CheckBox checkBoxOregano;
    CheckBox checkBoxRoasted;
    CheckBox checkBoxMozarella;
    CheckBox checkBoxSpinach;
    CheckBox checkBoxTomatoes;
    EditText textViewTotalSandwich;
    TextView count_sandwiches;
    int totalSandwich=0;
    int counterSandwich=0;
    ArrayList<Parcelable> listParcelable = new ArrayList<Parcelable>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareCheckBoxes();
        prepareButtonPlaceOrder();

        prepareText();
        Bundle bundle = this.getIntent().getExtras();
        if(bundle!=null) {
            if (bundle.getParcelableArrayList("parcelableList") != null) {
                listParcelable = bundle.getParcelableArrayList("parcelableList");
            Log.i(LOG_TAG,"Entro al bundle sise lita="+listParcelable.size());
            }

        }

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


    }

    private void prepareText() {
        Log.i(LOG_TAG, "totalSandwich="+totalSandwich);
        textViewTotalSandwich =(EditText)findViewById(R.id.edit_text_sandwich_count);


        count_sandwiches=(TextView)findViewById(R.id.counter_sandwiches);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        totalSandwich = settings.getInt("totalSandwich",0);
        counterSandwich = settings.getInt("counterSandwich",0);
        if(totalSandwich>0){
            textViewTotalSandwich.setText(totalSandwich+"");
            if(counterSandwich>0){
                count_sandwiches.setText(""+counterSandwich+" of "+totalSandwich );
            }
        }else{
            count_sandwiches.setText("");
        }



    }



    @Override
    protected void onPause() {
        super.onPause();


        Log.i("onPause", "Dentro de OnPause()");
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putInt("totalSandwich",Integer.parseInt(textViewTotalSandwich.getText().toString().trim()));
        editor.putInt("counterSandwich",counterSandwich+1);

        // Commit the edits!
        editor.commit();
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
                String order="\n";
                ArrayList<String> toppings =new ArrayList<String>();
                String toppingsOptions="";
                RadioGroup rg = (RadioGroup)findViewById(R.id.radio_group_bread);
                int selectedId = rg.getCheckedRadioButtonId();
                RadioButton radioSandwich = (RadioButton) findViewById(selectedId);

                order+="A sanwich " +radioSandwich.getText().toString().toUpperCase();

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

                OrderBeanParcelable orderBeanParcelable =new OrderBeanParcelable();

                if(!toppingsOptions.isEmpty()){
                    orderBeanParcelable.setNameSandwich(order);
                    orderBeanParcelable.setToppingsOptions(toppingsOptions);
                }else{
                    orderBeanParcelable.setNameSandwich(order);
                }

                 listParcelable.add(orderBeanParcelable);


                if(totalSandwich==0){
                    totalSandwich = Integer.parseInt(textViewTotalSandwich.getText().toString().trim());
                }
                Intent intent;
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("parcelableList",listParcelable);
                Log.i(LOG_TAG, "clik-listParcelable.size()="+listParcelable.size());
                Log.i(LOG_TAG, "clik-totalSandwich=" + totalSandwich);
                if(listParcelable.size() < totalSandwich) {

                    intent = new Intent(MainActivity.this, MainActivity.class);

                }else{
                    intent = new Intent(MainActivity.this, ConfirmationActivity.class);
                }

                intent.putExtras(bundle);
                Log.i(LOG_TAG, "antes de StartActivity");

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
