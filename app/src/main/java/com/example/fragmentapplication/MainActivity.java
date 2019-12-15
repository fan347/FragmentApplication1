package com.example.fragmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu_lab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add:
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final LayoutInflater inflater = getLayoutInflater();
                final View view = inflater.inflate(R.layout.layout_add,null);
                builder.setView(view);
                builder.setTitle("添加单词");
                builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText addWordThis = (EditText) view.findViewById(R.id.addWordThis);
                        EditText addWordMeaing = (EditText) view.findViewById(R.id.addMeaning);
                        EditText addWordInstance = (EditText) view.findViewById(R.id.addInstance);

                        String strWord = addWordThis.getText().toString();
                        String strMeaing = addWordMeaing.getText().toString();
                        String strInstance = addWordInstance.getText().toString();
                        Word word = new Word(strWord,strMeaing,strInstance);
                        WordDao wordDao = new WordDao(getBaseContext());
                        wordDao.delete(word);
                        wordDao.add(word);
                        Intent intent = new Intent(getBaseContext(),MainActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
            case R.id.menu_find:
                AlertDialog.Builder builderFind = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflaterFind = getLayoutInflater();
                final View viewFind = inflaterFind.inflate(R.layout.layout_find,null);
                builderFind.setView(viewFind);
                builderFind.setTitle("查询单词");

                builderFind.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builderFind.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText wordFind = (EditText) viewFind.findViewById(R.id.etFindWord);
                        String strwordFind = wordFind.getText().toString();
                        //WordDao wordDao = new WordDao(getBaseContext());
                       // ArrayList<Word> wordList = wordDao.findBlur(strwordFind);
                        Intent intent = new Intent(getBaseContext(),MainActivity.class);
                        intent.putExtra("findStr",strwordFind);
                        startActivity(intent);
                    }
                });
                builderFind.show();

                break;
            case  R.id.menu_ref:
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
