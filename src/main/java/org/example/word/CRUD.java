package org.example.word;

//What functions is going to be operated
public interface CRUD {
    public Object add();
    public int update(Object ob);
    public int delete(Object ob);
    public void select(int id);
}
