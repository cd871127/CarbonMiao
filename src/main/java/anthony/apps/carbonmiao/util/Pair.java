package anthony.apps.carbonmiao.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 键值对
 **/
public class Pair<K, V> {

    public Pair() {
        this(null, null);
    }

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    private K key;
    private V value;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }


    /**
     * 转换Map到List
     *
     * @param map 需要转换为List的Map
     * @param <K> 参数map的建类型
     * @param <V> 参数map的值类型
     * @return 转换后的List
     */
    public static <K, V> ArrayList<Pair<K, V>> mapToPairList(Map<K, V> map) {
        if (null == map || map.isEmpty())
            return null;
        ArrayList<Pair<K, V>> pairArrayList = new ArrayList<>();
        map.forEach((k, v) -> pairArrayList.add(new Pair<>(k, v)));
        return pairArrayList;
    }

    /**
     * 添加Pair到Map
     *
     * @param pair 需要添加到map的Pair
     * @param map  需要添加的map
     * @param <K>  参数map的建类型
     * @param <V>  参数map的值类型
     */
    public static <K, V> void addPairToMap(Pair<K, V> pair, Map<K, V> map) {
        assert map != null;
        map.put(pair.getKey(), pair.getValue());
    }


    /**
     * 添加Pair的List到Map
     *
     * @param pairList 需要添加到map的PairList
     * @param map      需要添加的map
     * @param <K>      参数map的建类型
     * @param <V>      参数map的值类型
     */
    public static <K, V> void addPairListToMap(List<Pair<K, V>> pairList, Map<K, V> map) {
        assert map != null;
        pairList.forEach((p) -> addPairToMap(p, map));
    }
}
