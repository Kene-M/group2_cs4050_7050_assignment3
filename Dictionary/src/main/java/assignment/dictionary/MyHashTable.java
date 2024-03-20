package assignment.dictionary;

/*

 */

//
import java.util.*;
import java.io.*;
import java.util.Dictionary;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.BiFunction;

/**

 */
public class MyHashTable<K,V>
//        
//        
{
    // You need to implement this class without using the
    // Hashtable class from Java (“java.util.Hashtable<K,V>”).

    AList<K> keyList;
    AList<V> valuesList;
    int numElems;

    // Maybe modify this class later to include hashing?
    // use .getLength() for compression -- +1 because 0 is ignored in AList???
    // How to implement hashing when remove() considered?
    /*
    Ryan had Key and Value individual fields, and a "Table" inner class
    where he used AList.
     */

    public MyHashTable () {
        numElems = 100;
        keyList = new AList<>(numElems);
        valuesList = new AList<>(numElems);
    }

    public int hash(K key) {
        //TODO DONT USE .HASHCODE()
        int index = key.hashCode(); // Get hash value of key.
        int compressedIndex = (index % (numElems - 1)) + 1; // 1 is added due to the first elem in AList being ignored.
        // 1 is removed from numElems for the same reason.
        return compressedIndex;
    }

    /** Adds a new entry to this dictionary. If the given search
     key already exists in the dictionary, replaces the
     corresponding value.
     @param key An object search key of the new entry.
     @param value An object associated with the search key.
     @return Either null if the new entry was added to the dictionary
     or the value that was associated with key if that value
     was replaced. */
    public V put(K key, V value)
    {
        // return myTable.put(key,value);

        /*if (keyList.getLength() == (numElems - 1)) {
            numElems *= 2;
        }
         */

        // Check if key already exists in dictionary.
        if (keyList.contains(key)) { // Replace value at key.
            int index = keyList.getPosition(key);
            V retVal = valuesList.getEntry(index);
            valuesList.add(index, value);

            return retVal;
        }
        else { // Add the key and the value.
            /*int index = hash(key);
            keyList.add(index, key);
            valuesList.add(index, value);*/

            keyList.add(key);
            valuesList.add(value);

            return null;
        }
    }


    /** Removes a specific entry from this dictionary.
     @param key An object search key of the entry to be removed.
     @return Either the value that was associated with the search key
     or null if no such object exists. */
    public V remove(K key)
    {
        // return myTable.remove(key);

        // Check if search key exists in dictionary.
        if (!(keyList.contains(key))) {
            return null;
        }

        int index = keyList.getPosition(key);
        keyList.remove(index);
        V retVal = valuesList.remove(index);

        return retVal;
    }

    /** Retrieves from this dictionary the value associated with a given
     search key.
     @param key An object search key of the entry to be retrieved.
     @return Either the value that is associated with the search key
     or null if no such object exists. */
    public V get(K key)
    {
        // return myTable.get(key);

        // Check if search key exists in dictionary.
        if (!(keyList.contains(key))) {
            return null;
        }

        int index = keyList.getPosition(key);
        V retVal = valuesList.getEntry(index);

        return retVal;
    }

    /** Sees whether a specific entry is in this dictionary.
     @param key An object search key of the desired entry.
     @return True if key is associated with an entry in the
     dictionary. */
    public boolean containsKey(Object key)
    {
        // return myTable.containsKey(key);
        return keyList.contains((K)(key));
    }

    /** Creates an iterator that traverses all search keys in this
     dictionary.
     @return An iterator that provides sequential access to the search
     keys in the dictionary. */
    /*public Iterator<K> getKeyIterator()
    {
        return myTable.keySet().iterator();
    }*/
    public AList<K> keySet() {
        return keyList;
    }

    /** Creates an iterator that traverses all values in this dictionary.
     @return An iterator that provides sequential access to the values
     in this dictionary. */
    /*public Iterator<V> getValueIterator()
    {
        return myTable.values().iterator();
    }*/
    public AList<V> values() {
        return valuesList;
    }

    /** Sees whether this dictionary is empty.
     @return True if the dictionary is empty. */
    public boolean isEmpty()
    {
        // return myTable.isEmpty();
        return keyList.isEmpty();
    }

    /** Gets the size of this dictionary.
     @return The number of entries (key-value pairs) currently
     in the dictionary. */
    public int size()
    {
        // return myTable.size();
        return keyList.getLength();
    }

    /** Removes all entries from this dictionary. */
    public void clear()
    {
        // myTable.clear();
        keyList.clear();
        valuesList.clear();
    }

    // Use the equals method of the map.
    public boolean equals(Object other)
    {
        /* ??????????????
        "Other" is another hashtable, need to check if they are equal.
        Check if null, same reference(?), if the keys and values are same, etc.
         */
        // return myTable.equals(other);

        if (other == null)
            return false;

        // MORE CODE ------------------------------------

        return true;
    }

    // Use the toString method of the map.
    public String toString()
    {
        // return myTable.toString();

        /*
        Loops through the hashTable and appends the keys and values together as a string.
         */
        String representation = "{";

        for (int i = 1; i <= keyList.getLength(); i++) {
            representation += keyList.getEntry(i) + ": " + valuesList.getEntry(i);

            if (i != keyList.getLength())
                representation += "\n";
        }

        representation += "}";

        return representation;
    }
}