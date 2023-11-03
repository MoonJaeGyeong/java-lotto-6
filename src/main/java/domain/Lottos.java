package domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(String numberOfPurchases){
        createLottos(Integer.parseInt(numberOfPurchases));
    }
    private void createLottos(int numberOfPurchases){
        for(int i=0; i<numberOfPurchases; i++){
            Lotto lotto = new Lotto(Randoms.pickUniqueNumbersInRange(1,45,6));
            lottos.add(lotto);
        }
    }
    public List<Lotto> getLottos() {
        return lottos;
    }
}
