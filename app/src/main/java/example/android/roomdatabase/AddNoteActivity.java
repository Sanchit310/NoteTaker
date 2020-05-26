package example.android.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "example.android.roomdatabase.EXTRA_ID";
    public static final String EXTRA_TITLE = "example.android.roomdatabase.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "example.android.roomdatabase.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "example.android.roomdatabase.EXTRA_PRIORITY";
    public Boolean isBookmarked ;
//    public static final int RESULT_OK = 1;

    EditText eTitle, eDescription;
    NumberPicker ePriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        eTitle = findViewById(R.id.notetitle);
        eDescription = findViewById(R.id.notedes);
        ePriority = findViewById(R.id.priority);

        ePriority.setMaxValue(10);
        ePriority.setMinValue(1);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)){

            setTitle("Edit Note");
            eTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            eDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            ePriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        }
        else {
            setTitle("Add Note");
        }




    }

    public void saveNote(){
        String title = eTitle.getText().toString();
        String description = eDescription.getText().toString();
        int priority = ePriority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()){

            Toast.makeText(this,"Please fill all entries",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);


        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if( id != -1){
            data.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK,data);
        finish();


    }

//    public Boolean saveBookmark(MenuItem item){
//
//        if(isBookmarked = false){
//            isBookmarked = true;
//            item.setIcon(R.drawable.ic_star_border_black_24dp);
//            return isBookmarked;
//        }
//        else{
//            isBookmarked = false;
//            item.setIcon(R.drawable.ic_star_black_24dp);
//            return isBookmarked;
//
//        }
//
//    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_list,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.savebtn:
                saveNote();
                return true;

//            case R.id.bookmark:
//                saveBookmark(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
