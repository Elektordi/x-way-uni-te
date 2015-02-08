package net.elektordi.modbusdroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.ToggleButton;
import fr.ig2i.jxway.JXWayAddress;
import fr.ig2i.jxway.drivers.XipDriver;
import fr.ig2i.jxway.reply.GenericXWayReply;
import fr.ig2i.jxway.requests.WriteInternalBitRequest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends Activity {

    XipDriver xip;
    JXWayAddress addr;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTitle("Connexion en cours...");
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        try {
            xip = new XipDriver("192.168.209.251", new JXWayAddress(8, 40));
        } catch (IOException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        addr = new JXWayAddress(9, 3);

        setContentView(R.layout.main);
        setTitle(getString(R.string.title));

        ((ToggleButton) findViewById(R.id.loco1)).setOnClickListener(new LocoClick(1));
        ((ToggleButton) findViewById(R.id.loco2)).setOnClickListener(new LocoClick(2));
        ((ToggleButton) findViewById(R.id.loco3)).setOnClickListener(new LocoClick(3));
    }

    @Override
    protected void onDestroy() {
        try {
            xip.close();
        } catch (IOException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.onDestroy();
    }

    private void showToast(String msg) {
        Toast.makeText(this.getApplicationContext(), msg, 100).show();
    }

    private class LocoClick implements OnClickListener {

        private int loco;

        public LocoClick(int loco) {
            this.loco = loco;
        }

        public void onClick(View view) {
            ToggleButton tb = (ToggleButton) view;
            boolean v = tb.isChecked();
            /*
             * String s = " locomotive n°" + loco + " !"; if (v) { s =
             * "Démarrage " + s; } else { s = "Arrêt " + s; } showToast(s);
             */
            GenericXWayReply reply = xip.transmit(addr, new WriteInternalBitRequest(loco, v));
        }
    }
}
