package firstlook.gohoo.utacarparkingsystem.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import firstlook.gohoo.utacarparkingsystem.R;

public class viewreservations extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView date;

    public TextView getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date.setText(date);
    }

    public TextView getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid.setText(transactionid);
    }

    TextView transactionid;
    public viewreservations(View itemview){
        super(itemview);
        date = itemview.findViewById(R.id.date);
        transactionid = itemview.findViewById(R.id.transacationid);
    }
    @Override
    public void onClick(View view) {

    }
}
