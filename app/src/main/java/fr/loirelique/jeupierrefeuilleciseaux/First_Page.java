package fr.loirelique.jeupierrefeuilleciseaux;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.view.ActionMode;
import android.view.animation.Animation;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Random;

public class First_Page extends AppCompatActivity {

    private TextView firt_page_text ;
    private TextView firt_page_text_bot;
    private ImageView firt_page_image_bot;
    private TextView  firt_page_text_select;

    private Button firt_page_btnPierre;


    private Button firt_page_btnFeuille;
    private Button firt_page_btnCiseaux;
    private TextView first_page_text_playerNameScore ;
    private TextView first_page_text_botNameScore;



    private AnimationDrawable anim_scissor_yellow;
    private AnimationDrawable anim_sheet_yellow;
    private AnimationDrawable anim_rock_yellow;

    private String player = "";


    int result_bot = 0 ;
    int result_player = 0 ;

    int manche = 6;

    int reponse = 0;
    int min = 0;
    int max = 3;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);


        //Variable
        firt_page_text = findViewById(R.id.first_page_text_intro);
        firt_page_text_bot = findViewById(R.id.first_page_text_afficheBotResulta);
        firt_page_image_bot = findViewById(R.id.firs_page_imgview_bot);
        firt_page_text_select = findViewById(R.id.first_page_text_select);
        firt_page_btnPierre = findViewById(R.id.first_page_btn_pierre);
        firt_page_btnFeuille = findViewById(R.id.first_page_btn_feuille);
        firt_page_btnCiseaux = findViewById(R.id.first_page_btn_ciseaux);
        first_page_text_playerNameScore = findViewById(R.id.first_page_text_playerNameScore);
        first_page_text_botNameScore = findViewById(R.id.first_page_text_botNameScore);


        //TEST ANIMATION IMAGE PAR IMAGE
        firt_page_btnPierre.setBackgroundResource(R.drawable.anim_rock_yellow);
        anim_rock_yellow = (AnimationDrawable) firt_page_btnPierre.getBackground();

        //TEST ANIMATION VECTORIEL

        //firt_page_btnFeuille.setBackgroundResource(R.drawable.anim_sheet_yellow);
        //anim_sheet_yellow = (AnimatedVectorDrawable) firt_page_btnFeuille.getBackground();

        // Animation par image feuille
        firt_page_btnFeuille.setBackgroundResource(R.drawable.anim_sheet_yellow);
        anim_sheet_yellow = (AnimationDrawable) firt_page_btnFeuille.getBackground();


        // Animation par image ciseaux
        firt_page_btnCiseaux.setBackgroundResource(R.drawable.anim_scissor_yellow);
        anim_scissor_yellow = (AnimationDrawable) firt_page_btnCiseaux.getBackground();



        //Recupération de données depuis la page main
        player = getIntent().getStringExtra("player");

        //Mise à jour du nom du joueur et bot
        first_page_text_playerNameScore.setText( player +" ("+ result_player +")" );

        first_page_text_botNameScore.setText( "Bot ("+ result_bot +")" );

        //lancement des boutton
        initialiseBtn();


    }

    @Override
    protected void onStart() {
        super.onStart();

        anim_rock_yellow.start();
        anim_scissor_yellow.start();
        anim_sheet_yellow.start();

    }


    public void startGame(){

        Random random = new Random();

        int value = min + random.nextInt(max - min) + 1;
        System.out.println(value);

        if (value == 1) {

            firt_page_image_bot.setImageResource(R.drawable.rock_yellow1);
            firt_page_text_bot.setText("Pierre ");
            System.out.println("Pierre");
            value = 1;

        } else if (value == 2) {

            firt_page_image_bot.setImageResource(R.drawable.sheet_yellow1);
            firt_page_text_bot.setText("Feuille");
            System.out.println("Feuille");
            value = 2;

        } else if (value == 3) {

            firt_page_image_bot.setImageResource(R.drawable.scissor_yellow1);
            firt_page_text_bot.setText("Ciseaux");
            System.out.println("Ciseaux");
            value = 3;
        }



        if(reponse == value)
        {

            if(value == 1) {

                System.out.println("Egalité avec Pierre");
                firt_page_text.setText("Egalité avec Pierre");


            }
            else if(value == 2) {
                System.out.println("Egalité avec Feuille");
                firt_page_text.setText("Egalité avec Feuille");

            }
            else if(value == 3) {
                System.out.println("Egalité avec Ciseaux");
                firt_page_text.setText("Egalité avec Ciseaux");

            }
        }
        else if(reponse == 1 && value == 2) {

            System.out.println(" Le Bot qui gagne avec Feuille");
            firt_page_text.setText(R.string.first_page_text_BotFeuilleWin);

            result_bot ++ ;
            first_page_text_botNameScore.setText( "Bot ("+ result_bot +")" );

            if(result_bot >= manche){

                firt_page_text.setText("Je joueur à: " + result_player +" point \n:VS:\n Le bot à: "+ result_bot+"");

                result_player = 0 ;
                result_bot = 0 ;

                first_page_text_botNameScore.setText( "Bot ("+ result_bot +")" );
                first_page_text_playerNameScore.setText( player +" ("+ result_player +")" );

            }
        }
        else if(reponse == 1 && value == 3) {

            System.out.println("Le joueur qui gagne avec Pierre");
            firt_page_text.setText(R.string.first_page_text_PlayerPierreWin);

            result_player ++ ;
            first_page_text_playerNameScore.setText( player +" ("+ result_player +")" );

            if(result_player >= manche){

                firt_page_text.setText("Je joueur à: " + result_player +" point \n:VS:\n Le bot à: "+ result_bot+"");

                result_player = 0 ;
                result_bot = 0 ;

                first_page_text_botNameScore.setText( "Bot ("+ result_bot +")" );
                first_page_text_playerNameScore.setText( player +" ("+ result_player +")" );


            }
        }
        else if(reponse == 2 && value == 1) {
            System.out.println("Le joueur qui gagne avec Feuille");
            firt_page_text.setText(R.string.first_page_text_PlayerFeuilleWin);

            result_player ++ ;
            first_page_text_playerNameScore.setText( player +" ("+ result_player +")" );

            if(result_player >= manche){

                firt_page_text.setText("Je joueur à: " + result_player +" point \n:VS:\n Le bot à: "+ result_bot+"");

                result_player = 0 ;
                result_bot = 0 ;

                first_page_text_botNameScore.setText( "Bot ("+ result_bot +")" );
                first_page_text_playerNameScore.setText( player +" ("+ result_player +")" );

            }

        }

        else if(reponse == 2 && value == 3) {

            System.out.println("Le Bot qui gagne avec Ciseaux");
            firt_page_text.setText(R.string.first_page_text_BotciseauxWin);

            result_bot ++ ;

            first_page_text_botNameScore.setText( "Bot ("+ result_bot +")" );

            if(result_bot >= manche) {

                firt_page_text.setText("Je joueur à: " + result_player + " point \n:VS:\n Le bot à: " + result_bot + "");

                result_player = 0;
                result_bot = 0;

                first_page_text_botNameScore.setText( "Bot ("+ result_bot +")" );
                first_page_text_playerNameScore.setText( player +" ("+ result_player +")" );
            }


            }
        else if(reponse == 3 && value == 1) {

            System.out.println("Le Bot qui gagne avec Pierre");
            firt_page_text.setText(R.string.first_page_text_BotPierreWin);

            result_bot ++ ;

            first_page_text_botNameScore.setText( "Bot ("+ result_bot +")" );

            if(result_bot >= manche) {

                firt_page_text.setText("Je joueur à: " + result_player + " point \n:VS:\n Le bot à: " + result_bot + "");

                result_player = 0;
                result_bot = 0;

                first_page_text_botNameScore.setText( "Bot ("+ result_bot +")" );
                first_page_text_playerNameScore.setText( player +" ("+ result_player +")" );


            }

            }

        else if(reponse == 3 && value == 2) {

            System.out.println("Le joueur qui gagne avec Ciseaux");
            firt_page_text.setText(R.string.first_page_text_PlayerCiseauxWin);

            result_player ++ ;

            first_page_text_botNameScore.setText( "Bot ("+ result_bot +")" );
            first_page_text_playerNameScore.setText( player +" ("+ result_player +")" );

            if(result_player >= manche){

                firt_page_text.setText("Je joueur à: " + result_player +" point \n:VS:\n Le bot à: "+ result_bot+"");

                result_player = 0 ;
                result_bot = 0 ;

                first_page_text_botNameScore.setText( "Bot ("+ result_bot +")" );
                first_page_text_playerNameScore.setText( player +" ("+ result_player +")" );
            }

        }
    }




    public void initialiseBtn(){
        //Debut du OnClick
        firt_page_btnPierre.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v )
            {
                firt_page_text_select.setText("Tu as bien cliquer sur le bouton Pierre.");
                reponse = 1;
                startGame();



            }
        });
        //Fin du OnClick

        //Debut du OnClick
        firt_page_btnFeuille.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v )
            {
                firt_page_text_select.setText("Tu as bien cliquer sur le bouton Feuille.");
                reponse = 2 ;
                startGame();

            }
        });
        //Fin du OnClick

        //Debut du OnClick
        firt_page_btnCiseaux.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v )
            {
                firt_page_text_select.setText("Tu as bien cliquer sur le bouton Ciseaux.");
                reponse = 3 ;
                startGame();
            }
        });
        //Fin du OnClick



    }




    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

}
