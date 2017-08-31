package fragmenta;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vukhachoi.fragment.R;

/**
 * Created by Vu Khac Hoi on 8/26/2017.
 */



public class fragment_b extends Fragment {

    EditText txt;
    EditText edt;
    Button btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_b,container,false);
        edt= view.findViewById(R.id.edtnhap_b);
        btn=view.findViewById(R.id.btnclickb);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt=getActivity().findViewById(R.id.edtnhap_a);
                txt.setText(edt.getText());
                Toast.makeText(getActivity(), edt.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
