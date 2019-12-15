package com.example.fragmentapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

public class TitleFragment extends Fragment{
    private ListView wordListView;
    private FragmentActivity activitys;
    private WordAdapter adapter;
    private List<Word> wordList = new ArrayList<>();

    @Override
        public void onCreateContextMenu(android.view.ContextMenu menu, View view, android.view.ContextMenu.ContextMenuInfo info){

            new MenuInflater(getActivity()).inflate(R.menu.menu_content, menu);
            super.onCreateContextMenu(menu,view,info);
        }
        @Override
        public boolean onContextItemSelected(@NonNull MenuItem item) {
            AdapterView.AdapterContextMenuInfo infor = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            final int id=infor.position;
            switch (item.getItemId()){
                case R.id.menu_delete:
                    AlertDialog.Builder builderDelte= new AlertDialog.Builder(getContext());
                    LayoutInflater inflaterDelete = getLayoutInflater();
                    final View viewFind = inflaterDelete.inflate(R.layout.layout_delete,null);
                    builderDelte.setView(viewFind);
                    builderDelte.setTitle("删除单词");
                    builderDelte.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });
                    builderDelte.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            WordDao wordDao = new WordDao(getContext());
                            wordDao.delete(wordList.get(id));
                            Intent intent = new Intent(getContext(),MainActivity.class);
                            startActivity(intent);
                        }
                    });
                   builderDelte.show();
                    break;
                case R.id.menu_update:

                    AlertDialog.Builder builderUpdate= new AlertDialog.Builder(getContext());
                    LayoutInflater inflaterUpdate = getLayoutInflater();
                    final View viewUpdate = inflaterUpdate.inflate(R.layout.layout_updata,null);

                    builderUpdate.setView(viewUpdate);

                    final Word word = wordList.get(id);
                    final EditText wordThis = (EditText) viewUpdate.findViewById(R.id.updateWords);
                    final EditText wordMeaing= (EditText) viewUpdate.findViewById(R.id.updateMeaning);
                    final EditText wordInstance= (EditText) viewUpdate.findViewById(R.id.updateInstance);
                    wordThis.setText(word.getWordThis());
                    wordMeaing.setText(word.getWordMeaning());
                    wordInstance.setText(word.getWordInstance());

                    builderUpdate.setTitle("修改单词");
                    builderUpdate.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builderUpdate.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String strwordThis = wordThis.getText().toString();
                            String strwordMeaing = wordMeaing.getText().toString();
                            String strwordInstance = wordInstance.getText().toString();

                            Word wordChange = new Word(strwordThis,strwordMeaing,strwordInstance);
                            WordDao wordDao = new WordDao(getContext());
                            wordDao.update(word,wordChange);
                            Intent intent = new Intent(getContext(),MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    builderUpdate.show();

                    break;
            }
            return super.onContextItemSelected(item);
        }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_title,container,false);
        activitys = this.getActivity();
        WordDao wordDao = new WordDao(getContext());
        Intent intent =getActivity().getIntent();
        String strfind = intent.getStringExtra("findStr");
        if(strfind==""||strfind==null){
            wordList = wordDao.findAll();
        }
        else{
            wordList=wordDao.findBlur(strfind);
        }
        adapter = new WordAdapter(activitys,R.layout.list_view_word_item,wordList);
        wordListView = (ListView)view.findViewById(R.id.list_view_word) ;
        registerForContextMenu(wordListView);
        wordListView.setAdapter(adapter);
        wordListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Word content = wordList.get(position);
                if (activitys.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    ContentFragment cf = (ContentFragment) activitys
                            .getSupportFragmentManager().findFragmentById(
                                    R.id.fm_content);
                    cf.setContent(content.getWordThis(),content.getWordMeaning(),content.getWordInstance());
                } else {
                    Intent intent = new Intent(activitys, ContentActivity.class);

                    intent.putExtra("wordthis", content.getWordThis());
                    intent.putExtra("wordmeaing", content.getWordMeaning());
                    intent.putExtra("wordinstance", content.getWordInstance());
                    activitys.startActivity(intent);
                }

            }
        });
        return view;
    }
}

