package com.adamgrieger.pythoneasydocs;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by Adam on 8/2/2015.
 */
public class CardRecyclerAdapter extends RecyclerView.Adapter<CardRecyclerAdapter.ViewHolder> {

    private PythonDocument[] availableDocs;
    private int tabPosition;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView vDocCard;
        public TextView vPythonVersion;
        public TextView vReleaseDate;
        public TextView vDocFormat;
        public TextView vDownloadSize;

        public ViewHolder(View v) {
            super(v);
            this.vDocCard = (CardView) v.findViewById(R.id.doc_card);
            this.vPythonVersion = (TextView) v.findViewById(R.id.python_version);
            this.vReleaseDate = (TextView) v.findViewById(R.id.release_date);
            this.vDocFormat = (TextView) v.findViewById(R.id.doc_format);
            this.vDownloadSize = (TextView) v.findViewById(R.id.download_size);
        }
    }

    public CardRecyclerAdapter(PythonDocument[] mDataset, int position) {
        availableDocs = mDataset;
        tabPosition = position;
    }

    @Override
    public CardRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_doc_download, parent, false);
        return new CardRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CardRecyclerAdapter.ViewHolder holder, int position) {
        PythonDocument pd = availableDocs[position];
        holder.vPythonVersion.setText("Python " + pd.getPythonVersion());
        holder.vReleaseDate.setText("Release Date: " + pd.getReleaseDate());

        if (tabPosition == 0) {
            holder.vDocFormat.setText("Format: HTML");
            holder.vDownloadSize.setText("Download Size: " + pd.getSizeHTML());
        } else if (tabPosition == 1) {
            holder.vDocFormat.setText("Format: PDF");
            holder.vDownloadSize.setText("Download Size: " + pd.getSizePDF());
        } else {
            holder.vDocFormat.setText("Format: TXT");
            holder.vDownloadSize.setText("Download Size: " + pd.getSizeTXT());
        }
    }

    @Override
    public int getItemCount() {
        return availableDocs.length;
    }
}
