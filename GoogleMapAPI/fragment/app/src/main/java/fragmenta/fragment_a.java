package fragmenta;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vukhachoi.fragment.R;

/**
 * Created by Vu Khac Hoi on 8/26/2017.
 */

public class fragment_a  extends Fragment{
datadelete isdelete;
TextView txt;
  public   EditText edt;
   public Button btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        isdelete= (datadelete) getActivity();
        View view= inflater.inflate(R.layout.fragmenta,container,false);
        edt= view.findViewById(R.id.edtnhap_a);
        btn=view.findViewById(R.id.btnclicka);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt=getActivity().findViewById(R.id.txt);
                txt.setText(edt.getText());
               isdelete.IsDelete(true);
            }
        });
        return view;

    }


    public  void settext(String noidung)
    {
        edt.setText(noidung);
    }
}
