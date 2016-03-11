package annuaire.lecteurmp3;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.*;
import android.widget.*;
import java.util.concurrent.*;
import android.media.*;
import android.os.*;

public class Lecteur extends AppCompatActivity
{

        private MediaPlayer mp;
        private double debut=0;
        private Handler hdl = new Handler();
        private TextView textview_1;
        private  ImageView image_view;
        private Button boutton_1;
        private Button boutton_2 ;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);

            // findView des Bouttons et des TextView
            findView();
            boutton_1.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick ( View v )
                {
                  Log.d( "debug", "onclick" );
                  mp.pause( );
                }
            });//bt1

            boutton_2.setOnClickListener( new View.OnClickListener()
            {
                @Override
                public void onClick( View v )
                { Log.d("debug", "onclick2");
                  mp.start( ) ;
                  //Log.d("debug0", "test");
                  debut =mp.getCurrentPosition() ;
                  textview_1.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes((long) debut),TimeUnit.MILLISECONDS.toSeconds((long)debut)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) debut))));
                  hdl.postDelayed ( MiseaJourDuree , 0 );
                   Log.d("debug3", TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) debut)) + "");
                }
            });//bt2
        }//onCreate

        private Runnable MiseaJourDuree = new Runnable()
        {
            public void run()
            {
                Log.d("debug", "run effect") ;
                debut =  mp.getCurrentPosition( ) ;
                textview_1.setText(String.format("%d:%d",TimeUnit.MILLISECONDS.toMinutes((long) debut),TimeUnit.MILLISECONDS.toSeconds((long)debut)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) debut))));
                hdl.postDelayed( this, 0 );
            }//run
        };//Runnable

        @Override
        public boolean onCreateOptionsMenu(Menu menu)
        {
            getMenuInflater().inflate(R.menu.menu_main, menu) ;
            return true;
        }

        public void findView()
        {
            boutton_1 = (Button) findViewById(R.id.boutton_1);
            boutton_2 =(Button)findViewById(R.id.boutton_2);
            textview_1 =(TextView)findViewById(R.id.tv1) ;

            mp =  MediaPlayer.create(this, R.raw.sincity);
            //Log.d("debug", "end find v");
        } // fin finView

        @Override

        public boolean onOptionsItemSelected( MenuItem m )
        {
            double item_menu =m.getItemId( ) ;
            if (item_menu==R.id.action_settings)
            {
                return true;
            }
            return super.onOptionsItemSelected(m);
        }

}//public class Lecteur extends AppCompatActivity