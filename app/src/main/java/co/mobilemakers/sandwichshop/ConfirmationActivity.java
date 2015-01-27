package co.mobilemakers.sandwichshop;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by root on 26/01/15.
 */
public class ConfirmationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        Bundle bundle = this.getIntent().getExtras();
        String order = bundle.getString("order");

    }
}
