//package com.bignerdranch.android.criminalintent;
//
//import android.app.Dialog;
//import android.app.TimePickerDialog;
//import android.os.Bundle;
//import android.support.v4.app.DialogFragment;
//import android.text.format.Time;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.TextClock;
//import android.widget.TimePicker;
//
//
//public class TimePickerFragment extends DialogFragment {
//
//    private static final String ARG_TIME = "time";
//
//    private TimePicker mTimePicker;
//
//    public static TimePickerFragment newInstance(Time time) {
//        Bundle args = new Bundle();
//        args.putSerializable(ARG_TIME, time);
//
//        TimePickerFragment fragment = new TimePickerFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Time time = (Time) getArguments().getSerializable(ARG_TIME);
//
//        TextClock textClock = TextClock.getInstance();
//        textClock.setTime(time);
//        int hour = textClock.get(TextClock.HOUR);
//        int minute = textClock.get(TextClock.MINUTE);
//        int second = textClock.get(TextClock.SECOND);
//
//        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time, null);
//
//        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_time_picker);
//        mTimePicker.init(hour, minute, second, null);
//
//        return new TimePickerDialog.Builder(getActivity())
//                .setView(v)
//                .setTitle(R.string.time_picker_title)
//                .setPositiveButton(android.R.string.ok, null)
//                .create();
//    }
//}
