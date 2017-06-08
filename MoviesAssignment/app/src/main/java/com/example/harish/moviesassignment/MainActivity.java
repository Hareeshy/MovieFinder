package com.example.harish.moviesassignment;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.harish.moviesassignment.Fragments.MovielistFragment;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity {

    EditText mName;
    TextView infoTv;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoTv= (TextView) findViewById(R.id.info);
        mName= (EditText) findViewById(R.id.searchEt);
        searchBtn= (Button) findViewById(R.id.searchBtn);
        mName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //        searchBtn.performClick();
                    MovielistFragment movielistFragment = new MovielistFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("movieName",mName.getText().toString());
                    movielistFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.activity_main,movielistFragment).addToBackStack(null).commit();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(),
                            InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    return true;
                }
                return false;
            }
        });
//        searchBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MovielistFragment movielistFragment = new MovielistFragment();
//                Bundle bundle=new Bundle();
//                bundle.putString("movieName",mName.getText().toString());
//                movielistFragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.activity_main,movielistFragment).addToBackStack(null).commit();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.searchIcon:
                mName.setVisibility(View.VISIBLE);
                infoTv.setVisibility(View.GONE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
