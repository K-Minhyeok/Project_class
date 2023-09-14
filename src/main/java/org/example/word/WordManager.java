package org.example.word;

import java.util.Scanner;

public class WordManager {
Scanner s = new Scanner(System.in);
WordCRUD wordCRUD;
WordManager(){
    wordCRUD = new WordCRUD(s);
}
    public int selecetMenu() {
        System.out.println("*** 영단어 마스터 ***");
        System.out.println("*******************");
        System.out.println("1. 모든 단어 보기");
        System.out.println("2. 수준별 단어 보기");
        System.out.println("3. 단어 검색");
        System.out.println("4. 단어 추가");
        System.out.println("5. 단어 수정");
        System.out.println("6. 단어 삭제");
        System.out.println("7. 파일 저장");
        System.out.println("0. 나가기");
        System.out.println("*******************");
        System.out.println("=> 원하는 메뉴는? ");

        int menu = s.nextInt();
        return menu;
    }

    public  void start(){
    wordCRUD.loadFile();
        while(true) {
            int a = selecetMenu();
            if(a==0){
                break;
            }
            if(a==1){
            wordCRUD.listAll();
            }

            if(a==2){
            wordCRUD.searchLevel();
            }

            if(a==3){
                wordCRUD.searchWord();

            }

            if(a==4){
                wordCRUD.addWord();
            }

            if(a==5){
                wordCRUD.updateItem();
            }

            if(a==6){
                wordCRUD.deleteItem();
            }
            if(a==7){
                wordCRUD.saveFile();
            }
        }
        wordCRUD.turnOff();
    }
}
