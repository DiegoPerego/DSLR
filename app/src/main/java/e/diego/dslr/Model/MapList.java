package e.diego.dslr.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego on 18/01/2018.
 */

public class MapList implements Serializable{

    private List<MyMap> mapList;

    public MapList(List<MyMap> mapList) {
        this.mapList = mapList;
    }

    public MapList() {
        this.mapList = new ArrayList<>();
    }

    public List<MyMap> getMapList() {
        return mapList;
    }

    public void setMapList(List<MyMap> mapList) {
        this.mapList = mapList;
    }

    public void  addMyMap(MyMap map){
        mapList.add(map);
    }

    public void switchingMapList (MapList enter, MapList exit){
        for (int i =0; i<enter.getMapList().size(); i++){
            exit.getMapList().add(enter.getMapList().get(i));
        }
    }
}
