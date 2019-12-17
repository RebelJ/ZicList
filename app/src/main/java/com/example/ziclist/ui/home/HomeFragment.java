package com.example.ziclist.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.ziclist.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;

    ArrayAdapter<String> adapter;
    List list = new ArrayList<String>();
    private String message = "";



    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.textCessionName);
        final ListView cessionList = root.findViewById(R.id.cessionList);

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list);
        cessionList.setAdapter(adapter);

        Button myButton = root.findViewById(R.id.buttonListCession);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                String value = textView.getText().toString();
                list.add(value);
                adapter.notifyDataSetChanged();
            }
        });



        cessionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

               // final String item = (String) parent.getItemAtPosition(position);

                    Intent intent = new Intent(getActivity(), ListActivity.class);
                    intent.putExtra("NameCession", textView.getText().toString());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                 //list.remove(item);
                // adapter.notifyDataSetChanged();
                // view.setAlpha(1);

            }

        });
        return root;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("message_key", this.message);
    }

/*
    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


        this.message = savedInstanceState.getString("message_key");

    }


    private class StableArrayAdapter extends ArrayAdapter<String> {
        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }
        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }
        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

*/
}