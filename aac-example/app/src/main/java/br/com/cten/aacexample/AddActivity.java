package br.com.cten.aacexample;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    private BorrowedListViewModel viewModel;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewModel = ViewModelProviders.of(this).get(BorrowedListViewModel.class);

        final EditText etItem = findViewById(R.id.et_item);
        final EditText etName = findViewById(R.id.et_name);
        final EditText etDate = findViewById(R.id.et_date);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String itemName = etItem.getText().toString();
                String personName = etName.getText().toString();
                /*int day = etDate.getDayOfMonth();
                int month = etDate.getMonth();
                int year = etDate.getYear();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);

                Date borrowDate =  calendar.getTime();*/

                BorrowModel model = new BorrowModel(itemName, personName, new Date());
                viewModel.addItem(model);

                AddActivity.this.finish();
            }
        });

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(AddActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                etDate.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        } );
    }
}
