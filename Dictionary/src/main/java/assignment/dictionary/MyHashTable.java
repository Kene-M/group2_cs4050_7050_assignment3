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
 * @author Kene, Skylar, Isaiah.
 */
public class MyHashTable<K,V> {
    // You need to implement this class without using the
    // Hashtable class from Java (“java.util.Hashtable<K,V>”).

    /*
     *Create a private "Pair" inner class that stores Key-Value pairs.
     *Create private Key and Value fields.
     Constructor only for (Key, Value) initialization. No setters/getters (use fields/AList).

     *Create an array of ALists. Each hash to an array index to store
     Key-Value "Pair".
     *If collision occurs, use AList add() to add Pair at the end.
     *If given duplicate key, either replace the corresponding value in Entry
     at index, OR replace the AList Pair at index (may use constructor or setter).
    ------------------------------------------------------------------------------
     *For iterators, you could create a new AList and traverse all buckets in the dictionary
     and add each key/value (depending on needed iterator) to it, and return the needed one.

     *To clear() properly. Just loop through and clear each AList in each index.
    */

    //private AList<K> keyList;
    //private AList<V> valuesList;

    //private AList<V>[] hashTable; // Array of buckets containing key-value Pairs.

    private final int numBuckets = 200;
    private AList<Pair<K,V>>[] hashTable; // Array of buckets containing key-value Pairs.

    /*

     */
    @SuppressWarnings("unchecked")
    public MyHashTable () {
        //keyList = new AList<>(numElems);
        //valuesList = new AList<>(numElems);

        //hashTable = (AList<V>[]) new AList[numBuckets];

        hashTable = (AList<Pair<K,V>>[]) new AList[numBuckets];

        // Instantiate the list in every bucket.
        for (int i = 0; i < numBuckets; i++) {
            hashTable[i] = new AList<>();
        }
    }

    // Returns the hash value for a specified key.
    public int hash(K key) {
        if (key == null)
            return -1;
        else if (key instanceof String) {
            return stringHash((String) key);
        }
        else if (key instanceof Integer) {
            return ((int) key); // The integer is the hash itself.
        }
        return customClassHash(key);
    }

    // Custom hash function for String type
    private int stringHash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += (int) key.charAt(i);
        }
        return hash;
    }

    // Custom general hash function for non Integer or String types.
    private int customClassHash(K key) {
        int hash = 0;
        String str = key.toString(); // Convert the object to string
        for (int i = 0; i < str.length(); i++) {
            hash += (int) str.charAt(i);
        }
        return hash;
    }

    // Compresses the given hash code to a valid location in the array of values
    private int getCompressedHash(int hashVal) {
        return (hashVal % numBuckets);
    }

    /**
     * Inner class representing a key-value entry for the dictionary.
     * @param <K>
     * @param <V>
     */
    private class Pair<K,V> {
        private K key;
        private V value;

        private Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /** Adds a new entry to this dictionary. If the given search
     key already exists in the dictionary, replaces the
     corresponding value.
     @param key An object search key of the new entry.
     @param value An object associated with the search key.
     @return Either null if the new entry was added to the dictionary
     or the value that was associated with key if that value
     was replaced. */
    public V put(K key, V value) {
        // Get the index of the Pair in the hashtable.
        int hashValue = hash(key);
        int index = getCompressedHash(hashValue);

        Pair<K,V> newEntry = new Pair<>(key, value); // The new pair in the bucket.
        boolean keyFound = false;
        AList<Pair<K,V>> bucket = hashTable[index];
        int keyPosition = 0;

        // Check if the specified key is already in the dictionary.
        for (int i = 1; i <= bucket.getLength() && !keyFound; i++) {
            Pair<K,V> currEntry = bucket.getEntry(i);
            K currKey = currEntry.key;

            // Get the position of the key in bucket, if found.
            if (currKey == key) {
                keyFound = true;
                keyPosition = i;
            }
        }

        // Check if key already exists in dictionary.
        if (keyFound) { // Replace value at key.
            V retVal = bucket.getEntry(keyPosition).value;
            bucket.replace(keyPosition, newEntry);

            return retVal;
        }
        else { // Add the key and the value.
            bucket.add(newEntry);
            return null;
        }

        // Check if key already exists in dictionary.
        /*if (keyList.contains(key)) { // Replace value at key.
            int index = keyList.getPosition(key);
            V retVal = valuesList.getEntry(index);
            valuesList.add(index, value);

            return retVal;
        }
        else { // Add the key and the value.
            /*     keyList.add(key);
            valuesList.add(value);*/

            /*int index = hash(key);
            keyList.add(index, key);
            valuesList.add(index, value);

            return null;
        }*/
    }


    /** Removes a specific entry from this dictionary.
     @param key An object search key of the entry to be removed.
     @return Either the value that was associated with the search key
     or null if no such object exists. */
    public V remove(K key) {
        // Get the index of the Pair in the hashtable.
        int hashValue = hash(key);
        int index = getCompressedHash(hashValue);

        boolean keyFound = false;
        AList<Pair<K,V>> bucket = hashTable[index];
        int keyPosition = 0;

        // Check if the specified key is in the dictionary.
        for (int i = 1; i <= bucket.getLength() && !keyFound; i++) {
            Pair<K,V> currEntry = bucket.getEntry(i);
            K currKey = currEntry.key;

            // Get the position of the key in bucket, if found.
            if (currKey == key) {
                keyFound = true;
                keyPosition = i;
            }
        }

        // Check if search key is not in the dictionary.
        if (!keyFound) {
            return null;
        }
        else {
            // Get the value associated with the key.
            V retVal = bucket.getEntry(keyPosition).value;

            // Remove the Pair at the specified key.
            bucket.remove(keyPosition);

            return retVal;
        }

        /*// Check if search key exists in dictionary.
        if (!(keyList.contains(key))) {
            return null;
        }

        int index = keyList.getPosition(key);
        keyList.remove(index);
        V retVal = valuesList.remove(index);

        return retVal;*/
    }

    /** Retrieves from this dictionary the value associated with a given
     search key.
     @param key An object search key of the entry to be retrieved.
     @return Either the value that is associated with the search key
     or null if no such object exists. */
    public V get(K key) {
        // Get the index of the Pair in the hashtable.
        int hashValue = hash(key);
        int index = getCompressedHash(hashValue);

        boolean keyFound = false;
        AList<Pair<K,V>> bucket = hashTable[index];
        int keyPosition = 0;

        // Check if the specified key is in the dictionary.
        for (int i = 1; i <= bucket.getLength() && !keyFound; i++) {
            Pair<K,V> currEntry = bucket.getEntry(i);
            K currKey = currEntry.key;

            // Get the position of the key in bucket, if found.
            if (currKey == key) {
                keyFound = true;
                keyPosition = i;
            }
        }

        // Check if key already exists in dictionary.
        if (keyFound) {
            return (bucket.getEntry(keyPosition).value);
        }
        else {
            return null;
        }

        /*// Check if search key exists in dictionary.
        if (!(keyList.contains(key))) {
            return null;
        }

        int index = keyList.getPosition(key);
        V retVal = valuesList.getEntry(index);

        return retVal;*/
    }

    /** Sees whether a specific entry is in this dictionary.
     @param key An object search key of the desired entry.
     @return True if key is associated with an entry in the
     dictionary. */
    public boolean containsKey(Object key) {
        // Get the index of the Pair in the hashtable.
        int hashValue = hash((K)(key));
        int index = getCompressedHash(hashValue);

        boolean keyFound = false;
        AList<Pair<K,V>> bucket = hashTable[index];

        // Check if the specified key is in the dictionary.
        for (int i = 1; i <= bucket.getLength() && !keyFound; i++) {
            Pair<K,V> currEntry = bucket.getEntry(i);
            K currKey = currEntry.key;

            if (currKey.equals((K)(key))) {
                keyFound = true;
                System.out.println("hi");
            }
        }
        return keyFound;

        // return keyList.contains((K)(key));
    }

    /** Creates an iterator that traverses all search keys in this
     dictionary.
     @return An iterator that provides sequential access to the search
     keys in the dictionary. */
    public AList<K> keySet() {
        AList<K> keyList = new AList<>();
        K currKey;

        // Traverse all the Pairs in the dictionary and add to a new list
        // which has an iterator already implemented.
        for (int i = 0; i < numBuckets; i++) {
            for (int j = 1; j <= hashTable[i].getLength(); j++) {
                currKey = hashTable[i].getEntry(i).key;
                keyList.add(currKey);
            }
        }

        return keyList;
        //return keyList;
    }

    /** Creates an iterator that traverses all values in this dictionary.
     @return An iterator that provides sequential access to the values
     in this dictionary. */
    public AList<V> values() {
        AList<V> valuesList = new AList<>();
        V currVal;

        // Traverse all the Pairs in the dictionary and add to a new list
        // which has an iterator already implemented.
        for (int i = 0; i < numBuckets; i++) {
            for (int j = 1; j <= hashTable[i].getLength(); j++) {
                currVal = hashTable[i].getEntry(i).value;
                valuesList.add(currVal);
            }
        }

        return valuesList;
        //return valuesList;
    }

    /** Sees whether this dictionary is empty.
     @return True if the dictionary is empty. */
    public boolean isEmpty()
    {
        if (size() == 0) {
            return true;
        }
        else {
            return false;
        }

        // return keyList.isEmpty();
    }

    /** Gets the size of this dictionary.
     @return The number of entries (key-value pairs) currently
     in the dictionary. */
    public int size()
    {
        int numElems = 0;

        // Count the pairs in the dictionary.
        for (int i = 0; i < numBuckets; i++) {
            for (int j = 1; j <= hashTable[i].getLength(); j++) {
                numElems++;
            }
        }

        return numElems;

        // return keyList.getLength();
    }

    /** Removes all entries from this dictionary. */
    public void clear() {
        // Traverse each bucket in the dictionary.
        for (int i = 0; i < numBuckets; i++) {
            // Clear each bucket.
            hashTable[i].clear();
        }

        // keyList.clear();
        // valuesList.clear();
    }

    /*
        "Other" may be another hashtable, this method checks each Pair is equal.
      */
    public boolean equals(Object other) {
        boolean isEqual = false;

        if (other instanceof MyHashTable<?,?>) {
            //return (this.rollno) == (((Student)other).rollno);

            // TODO Check if the entries are equal.

        }
        return isEqual;
    }

    /*
        Loops through the hashTable and appends the keys and values together as a string.
    */
    public String toString() {
        String representation = "{";

        // TODO Turn into String.
        // Concatenate each key-value line.
        for (int i = 0; i < numBuckets; i++) {
            for (int j = 1; j <= hashTable[i].getLength(); j++) {
                representation += hashTable[i].getEntry(j).key + ": " + hashTable[i].getEntry(j).value;

                representation += "\n";
            }
        }

        representation += "}";

        return representation;

        /*String representation = "{";

        for (int i = 1; i <= keyList.getLength(); i++) {
            representation += keyList.getEntry(i) + ": " + valuesList.getEntry(i);

            if (i != keyList.getLength())
                representation += "\n";
        }

        representation += "}";

        return representation;*/
    }
}