package firstlook.gohoo.utacarparkingsystem.viewHolder;

import android.content.ClipData;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import firstlook.gohoo.utacarparkingsystem.R;

public class viewParking extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView areaname;
    TextView spot;
    TextView type;

    public TextView getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname.setText(areaname);
    }

    public TextView getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot.setText(spot);
    }

    public TextView getType() {
        return type;
    }

    public void setType(String type) {
        this.type.setText(type);
    }

    public viewParking(View itemView) {
        super(itemView);
        this.areaname =itemView.findViewById(R.id.itemAreaname);
        this.spot = itemView.findViewById(R.id.itemspot);
        this.type = itemView.findViewById(R.id.itemtype);
    }

    @Override
    public void onClick(View view) {

    }
}
