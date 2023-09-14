package org.example.word;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;

import static java.lang.System.exit;

//This class is for specific implementation of every function of CRUD
//Kinda frame of CRUD
public class WordCRUD implements CRUD {
    ArrayList<Word> list;
    Scanner s;

    WordCRUD(Scanner s) {
        list = new ArrayList<>();
        this.s = s;
    }

    @Override
    public Object add() {
        //what to add
        System.out.println("=> 난이도(1,2,3) & 새 단어 입력: ");
        int level = s.nextInt();
        String word = s.nextLine();

        System.out.println("뜻 입력 : ");
        String meaning = s.nextLine();


        return new Word(0, level, word, meaning);

    }

    public void addWord() {
        //add the Object to the list

        Word one = (Word) add();
        list.add(one);
        System.out.println("새 단어가 단어장에 추가되었습니다.");
    }

    @Override
    public int update(Object ob) {
        return 0;
    }

    @Override
    public int delete(Object ob) {
        return 0;
    }

    @Override
    public void select(int id) {

    }

    public void listAll() {
        System.out.println("--------------------------");
        for (int i = 0; i < list.size(); i++) {
            System.out.print((i + 1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("--------------------------");

    }

    // 수정할 때, 입력받은 문자열이 있는 단어들만 출력해주는 함수
    public ArrayList<Integer> listAll(String keyword) {
        ArrayList<Integer> idlist = new ArrayList<>();
int j=0;
        System.out.println("--------------------------");
        for (int i = 0; i < list.size(); i++) {
            String word = list.get(i).getWord();
            if (!word.contains(keyword))
                continue;

            System.out.print((j + 1) + " ");
            System.out.println(list.get(i).toString());
            idlist.add(i);
            j++;
        }
        System.out.println("--------------------------");

        return idlist;
    }

    public void listAll(int level) {
        int j=0;
        System.out.println("--------------------------");
        for (int i = 0; i < list.size(); i++) {
            int ilevel = list.get(i).getLevel();
            if (ilevel==level){
                System.out.print((j + 1) + " ");
                System.out.println(list.get(i).toString());
                j++;
            }else {
                continue;
            }
        }
        System.out.println("--------------------------");
    }


    public void turnOff(){
        System.out.println("프로그램 종료! 다음에 만나요~ ");
        System.exit(1);
}

//수정할 단어 찾기
//입력받은 문자열이 있는 단어들 보여주기
//그 입력받은 문자열을 가진 단어들이 들어있는 Array에서 번호를 받아
// 그 번호의 단어를 받아 List에 있는 단어 수정
    public void updateItem() {
        System.out.println("=> 수정할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.println("=> 수정할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine(); //이거 없으면 뜻 입력 건너뛰고 바로 끝남
        System.out.println("=> 뜻 입력 : ");
        String meaning = s.nextLine();
        Word word = list.get(idlist.get(id-1));
        word.setMeaning(meaning);
        System.out.println("단어가 수정되었습니다. ");

    }

    public void deleteItem() {
        System.out.println("=> 삭제할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.println("=> 삭제할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine(); //이거 없으면 뜻 입력 건너뛰고 바로 끝남
        System.out.println("=> 삭제하겠습니까?(y/n) ");
        String ans = s.next();
        if(ans.equalsIgnoreCase("y")) {
            list.remove((int)idlist.get(id - 1));
            System.out.println("단어가 삭제되었습니다. ");
        }else{
            System.out.println("취소되었습니다. ");

        }

        }
    public void loadFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\study\\Project_word\\src\\word.txt"));
            String line;
            int count =0;

            while(true) {
                line = br.readLine(); //한 줄씩 읽어옴
                if(line ==null) {break;}
                String data[] = line.split("\\|"); //가져온 데이터 자르기
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];

                list.add(new Word(0,level,word,meaning));
                count++;
            }

                br.close();
            System.out.println("==> "+ count + "개 로딩 완료");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
        public void saveFile(){
            try {
                PrintWriter pr = new PrintWriter(new FileWriter("C:\\study\\Project_word\\src\\word.txt"));
                for(Word one:list){
                    pr.write(one.toFileString() + "\n");
                }

                pr.close();
                System.out.println("데이터 저장 완료");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    public void searchLevel() {
        System.out.println("원하는 레벨은? (1~3)");
        int level =s.nextInt();
        listAll(level);

    }

    public void searchWord() {
        System.out.println("찾으려는 단어는?");
        String tword =s.next();
        listAll(tword);

    }
}

