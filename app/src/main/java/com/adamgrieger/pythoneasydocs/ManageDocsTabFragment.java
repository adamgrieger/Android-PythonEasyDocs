package com.adamgrieger.pythoneasydocs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Fragment for storing the cards of each format of Python documentation.
 */
public class ManageDocsTabFragment extends Fragment {

    private static final String ARG_POSITION = "position";

    private int position;

    private PythonDocument[] availableDocs = {
            new PythonDocument("3.4.3"),
            new PythonDocument("3.3.6"),
            new PythonDocument("2.7.10")
    };

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static ManageDocsTabFragment newInstance(int position) {
        ManageDocsTabFragment mFragment = new ManageDocsTabFragment();
        Bundle mBundle = new Bundle();
        mBundle.putInt(ARG_POSITION, position);
        mFragment.setArguments(mBundle);

        return mFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_manage_docs_tab, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.card_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(rootView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CardRecyclerAdapter(availableDocs, position);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }
}
