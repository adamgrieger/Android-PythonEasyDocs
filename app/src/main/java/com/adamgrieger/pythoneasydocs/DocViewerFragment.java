package com.adamgrieger.pythoneasydocs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A placeholder fragment containing a simple view.
 */
public class DocViewerFragment extends Fragment {
    private static final String KEY_TITLE = "title";

    public DocViewerFragment() {
    }

    public static DocViewerFragment newInstance(String title) {
        DocViewerFragment f = new DocViewerFragment();

        Bundle args = new Bundle();

        args.putString(KEY_TITLE, title);
        f.setArguments(args);

        return (f);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doc_viewer, container, false);
    }
}
