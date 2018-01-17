package e.diego.dslr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import e.diego.dslr.Fragment.FavouritePlace;
import e.diego.dslr.Fragment.LongExpo;
import e.diego.dslr.Fragment.MyGear;

import e.diego.dslr.Util.ConstantsUtils;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            fragmentManager =  getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_gear:
                    fragmentTransaction.replace(R.id.frameLayout, new MyGear()).commit();
                    return true;
                case R.id.navigation_place:
                    fragmentTransaction.replace(R.id.frameLayout, new FavouritePlace()).commit();
                    return true;
                case R.id.navigation_nd:
                    fragmentTransaction.replace(R.id.frameLayout, new LongExpo()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_place);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ConstantsUtils.REQUEST_CODE_MAP) {
            if (resultCode == Activity.RESULT_OK)
                fragmentTransaction.replace(R.id.frameLayout, new FavouritePlace()).commit();
        }
    }

}
