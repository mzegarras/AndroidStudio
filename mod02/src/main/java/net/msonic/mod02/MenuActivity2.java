package net.msonic.mod02;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by manuelzegarra on 30/12/13.
 */
public class MenuActivity2 extends FragmentActivity {




    private DrawerLayout drawerLayout;
    private ListView drawer;
    private ActionBarDrawerToggle toggle;

    final String[] data ={"one","two","three"};
    final String[] fragments ={
            "net.msonic.mod02.FragmentOne",
            "net.msonic.mod02.FragmentTwo",
            "net.msonic.mod02.FragmentThree"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu2_activity);

        // Rescatamos el Action Bar y activamos el boton Home
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActionBar().getThemedContext(), android.R.layout.simple_list_item_1, data);

        final DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        final ListView navList = (ListView) findViewById(R.id.drawer);





        navList.setAdapter(adapter);
        navList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int pos,long id){
                /*drawer.setDrawerListener( new DrawerLayout.SimpleDrawerListener(){
                    @Override
                    public void onDrawerClosed(View drawerView){
                        super.onDrawerClosed(drawerView);

                    }
                });*/
                FragmentTransaction tx = getSupportFragmentManager().beginTransaction();

                tx.replace(R.id.main, Fragment.instantiate(MenuActivity2.this, fragments[pos]));
                tx.commit();

                drawer.closeDrawer(navList);
            }
        });

        // Sombra del panel Navigation Drawer
        drawer.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);


        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.main,Fragment.instantiate(MenuActivity2.this, fragments[0]));
        tx.commit();

        // Integracion boton oficial
        toggle = new ActionBarDrawerToggle(
                this, // Activity
                drawer, // Panel del Navigation Drawer
                R.drawable.ic_drawer, // Icono que va a utilizar
                R.string.app_name, // Descripcion al abrir el drawer
                R.string.hello_world // Descripcion al cerrar el drawer
        ){
            public void onDrawerClosed(View view) {
                // Drawer cerrado
                getActionBar().setTitle(getResources().getString(R.string.app_name));
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                // Drawer abierto
                getActionBar().setTitle("Menu");
                invalidateOptionsMenu();
            }
        };

        drawer.setDrawerListener(toggle);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }
}