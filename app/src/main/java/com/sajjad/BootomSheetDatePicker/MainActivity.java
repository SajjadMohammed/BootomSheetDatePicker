package com.sajjad.BootomSheetDatePicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    NumberPicker dayPicker;
    NumberPicker monthPicker;
    NumberPicker yearPicker;
    Button done;
    TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        dateTextView = findViewById(R.id.dateTextView);
        dateTextView.setOnClickListener(openBottomSheetListener);
    }

    View.OnClickListener openBottomSheetListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openBottomSheetDialog();
        }
    };

    public void openBottomSheetDialog() {
        final BottomSheetDialog datePickerSheet =
                new BottomSheetDialog(MainActivity.this, R.style.BottomSheetStyle);
        final View sheetView = getLayoutInflater().inflate(R.layout.date_picker, null);
        dayPicker = sheetView.findViewById(R.id.dayPicker);
        monthPicker = sheetView.findViewById(R.id.monthPicker);
        yearPicker = sheetView.findViewById(R.id.yearPicker);
        done = sheetView.findViewById(R.id.done);
        //
        setDefaultValuesToPicker();
        //
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerSheet.setDismissWithAnimation(true);
                datePickerSheet.dismiss();
            }
        });
        //
        datePickerSheet.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                dateTextView.setText(
                        String.format(Locale.US,
                                "%d/%d/%d",
                                dayPicker.getValue()
                                , monthPicker.getValue()
                                , yearPicker.getValue())
                );
            }
        });
        //
        sheetView.setBackgroundColor(Color.TRANSPARENT);
        datePickerSheet.setContentView(sheetView);
        datePickerSheet.show();
    }

    private void setDefaultValuesToPicker() {
        //
        yearPicker.setMinValue(1900);
        yearPicker.setMaxValue(Calendar.getInstance().get(Calendar.YEAR));
        //
        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
        //
        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(31);
        // set current date to picker
        yearPicker.setValue(Calendar.getInstance().get(Calendar.YEAR));
        monthPicker.setValue(Calendar.getInstance().get(Calendar.MONTH) + 1);
        dayPicker.setValue(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }
}