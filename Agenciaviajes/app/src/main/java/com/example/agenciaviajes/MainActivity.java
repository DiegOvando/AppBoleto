package com.example.agenciaviajes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Boleto boleto = new Boleto();

    private Button btnBoleto;
    private Button btnCerrar;
    private Spinner spnDestinos;
    private Spinner spnTipo;
    private TextView lblBoleto;
    private TextView tvDate;
    private TextView lblNum;
    private TextView lblDate;
    private TextView lblClient;
    private TextView lblDest;
    private TextView lblTipo;
    private TextView lblCosto;
    private TextView lblSubtotal;
    private TextView lblImpuesto;
    private TextView lblDescuento;
    private TextView lblTotal;
    private EditText etDate;
    private EditText txtNum;
    private EditText txtDate;
    private EditText txtClient;
    private EditText txtCosto;
    private EditText txtEdad;

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Spinner para tipo de viaje------------------------------------------------------------------------
        spnTipo = (Spinner) findViewById(R.id.spnTipo);
        ArrayAdapter<String> Adap=new
                ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,
                getResources().getStringArray(R.array.tipo));
        spnTipo.setAdapter(Adap);
        spnTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,"Selecciono el tipo " +
                                adapterView.getItemAtPosition(i).toString(),
                        Toast.LENGTH_SHORT).show();
                lblTipo.setText("Tipo viaje: " + Integer.parseInt(adapterView.getItemAtPosition(i).toString()));
                boleto.setTipoViaje(Integer.parseInt(adapterView.getItemAtPosition(i).toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //Spinner para los destinos-------------------------------------------------------------------------
        spnDestinos = (Spinner) findViewById(R.id.spnDestinos);
        ArrayAdapter<String> Adaptador=new
                ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,
                getResources().getStringArray(R.array.destinos));
        spnDestinos.setAdapter(Adaptador);
        spnDestinos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,"Selecciono el destino " +
                        adapterView.getItemAtPosition(i).toString(),
                        Toast.LENGTH_SHORT).show();
                lblDest.setText("Destino: " + adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //Date----------------------------------------------------------------------------------------------
        tvDate = (TextView) findViewById(R.id.lblDate);
        etDate = (EditText) findViewById(R.id.txtDate);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = day+"/"+month+"/"+year;
                tvDate.setText(date);
            }
        };

        etDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day+"/"+month+"/"+year;
                        etDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        //Funcion de Botton------------------------------------------------------------------------------------
        btnBoleto = (Button) findViewById(R.id.btnGenerar);
        btnCerrar = (Button)findViewById(R.id.btnCerrar);
        lblBoleto = (TextView) findViewById(R.id.lblBoleto);
        txtNum = (EditText) findViewById(R.id.txtNum);
        lblNum = (TextView) findViewById(R.id.lblNum);
        txtDate = (EditText) findViewById(R.id.txtDate);
        lblDate = (TextView) findViewById(R.id.lblDate);
        txtClient = (EditText) findViewById(R.id.txtClient);
        lblClient = (TextView) findViewById(R.id.lblClient);
        spnDestinos = (Spinner) findViewById(R.id.spnDestinos);
        lblDest = (TextView) findViewById(R.id.lblDest);
        spnTipo = (Spinner) findViewById(R.id.spnTipo) ;
        lblTipo = (TextView) findViewById(R.id.lblTipo);
        txtCosto = (EditText) findViewById(R.id.txtCosto);
        lblCosto = (TextView) findViewById(R.id.lblCosto);
        txtEdad = (EditText) findViewById(R.id.txtEdad);

        //Calculos
        lblSubtotal = (TextView) findViewById(R.id.lblSubtotal);
        lblImpuesto = (TextView) findViewById(R.id.lblImpuesto);
        lblDescuento = (TextView) findViewById(R.id.lblDescuento);
        lblTotal = (TextView) findViewById(R.id.lblTotal);

        btnBoleto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtNum.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this,"Favor de ingresar el no. boleto",
                            Toast.LENGTH_SHORT).show();
                }
                if(txtDate.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this,"Favor de ingresar la fecha",
                            Toast.LENGTH_SHORT).show();
                }
                if(txtClient.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this,"Favor de ingresar el nombre",
                            Toast.LENGTH_SHORT).show();
                }
                if(txtEdad.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this,"Favor de ingresar la edad",
                            Toast.LENGTH_SHORT).show();
                }
                if(txtCosto.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this,"Favor de ingresar el precio",
                            Toast.LENGTH_SHORT).show();
                }
                else {

                    lblBoleto.setText("Boleto" );
                    String txtNumber = txtNum.getText().toString();
                    lblNum.setText("No Boleto: " + txtNumber );
                    String txtFecha = txtDate.getText().toString();
                    lblDate.setText("Fecha: " + txtFecha);
                    String txtCliente = txtClient.getText().toString();
                    lblClient.setText("Nombre Cliente: " + txtCliente);
                    //Edad
                    int txtAge = Integer.parseInt(txtEdad.getText().toString());
                    //Precio
                    int txtPrecio = Integer.parseInt(txtCosto.getText().toString());
                    lblCosto.setText("Precio: $" + txtPrecio);
                    boleto.setPrecio(txtPrecio);

                    lblSubtotal.setText("Subtotal: " + boleto.calcularSubtotal());
                    lblImpuesto.setText("Impuesto (16%): " + boleto.calcularImpuesto());
                    lblDescuento.setText("Descuento: " + boleto.calcularDescuento(txtAge));
                    lblTotal.setText("Total a pagar: " + boleto.calcularTotal());
                }
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder confirmar = new AlertDialog.Builder(MainActivity.this);
                confirmar.setTitle("¿Cerrar App?");
                confirmar.setMessage("Se descartara toda la informacion. ");
                confirmar.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                confirmar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Nada
                    }
                }); confirmar.show();
            }
        });
    }
}
