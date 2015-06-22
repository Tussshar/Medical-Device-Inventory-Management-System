/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Network;

/**
 *
 * @author Tushar
 */
public class NetworkLocation {
    public enum LocationsForNetwork{
        BarnstableCounty("Barnstable"),
        BerkshireCounty("Berkshire"),
        BristolCounty("Bristol"),
        DukesCounty("Dukes"),
        EssexCounty("Essex"),
        FranklinCounty("Franklin"),
        HampdenCounty("Hampden"),
        MiddlesexCounty("Middlesex"),
        NantucketCounty("Nantucket"),
        NorfolkCounty("Norfolk");
        
        String value;
        
        private LocationsForNetwork(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
        
    }
}
