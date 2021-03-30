import java.util.ArrayList;
import java.util.Iterator;

import tester.Tester;

class ListOfLists<T> implements Iterable<T>{
  ArrayList<ArrayList<T>> list;
  
  ListOfLists() {
    list = new ArrayList<ArrayList<T>>();
  }
  
  int size() {
    return list.size();
  }
  
  void addNewList() {
    list.add(new ArrayList<T>());
  }
  
  public Iterator<T> iterator() {
    return new ListOfListsIterator<T>(this);
  }
  
  void add(int index,T object) {
    list.get(index).add(object);
  }
  
  ArrayList<T> get(int index) {
    return list.get(index);
  }
}

class ListOfListsIterator<T> implements Iterator<T> {
  int current;
  Iterator<T> currentIterator;
  ListOfLists<T> lol;
  
  ListOfListsIterator(ListOfLists<T> lol) {
    this.lol = lol;
    current = 0;
    if (current<lol.size()) {
      currentIterator = lol.get(current).iterator();
    }
    else {
      currentIterator = new ArrayList<T>().iterator();
    }
  }
  public boolean hasNext() {
    return currentIterator.hasNext();
  }
  
  public T next() {
    if (!currentIterator.hasNext()) {
      current = current + 1;
      if (current<this.lol.size()) {
        currentIterator = lol.get(current).iterator();
      }
      else {
        currentIterator = new ArrayList<T>().iterator();
      }
    }
    return currentIterator.next();
  }
  
  public void remove() {
    throw new UnsupportedOperationException("Remove is not supported");
  }
}

class ExamplesListOfLists {
  void testListOfLists(Tester t) {
    ListOfLists<Integer> lol = new ListOfLists<Integer>();
    //add 3 lists
    lol.addNewList();
    lol.addNewList();
    lol.addNewList();
    
    //add elements 1,2,3 in first list
    lol.add(0,1);
    lol.add(0,2);
    lol.add(0,3);
    
    //add elements 4,5,6 in second list
    lol.add(1,4);
    lol.add(1,5);
    lol.add(1,6);
    
    //add elements 7,8,9 in third list
    lol.add(2,7);
    lol.add(2,8);
    lol.add(2,9);
    
    //iterator should return elements in order 1,2,3,4,5,6,7,8,9
    int number = 1;
    for (Integer num:lol) {
        t.checkExpect(num,number);
        number = number + 1;
    }
}
}