package controller;

import camp.nextstep.edu.missionutils.Console;
import domain.LottoSystem;
import domain.Lottos;
import service.LottoService;
import util.Parser;
import util.Validate;
import view.LottoView;

import java.util.List;

public class LottoController {
    private final Parser parser = new Parser();
    private final Validate validate = new Validate();
    private final LottoService lottoService = new LottoService();
    private final int LOTTO_PRICE = 1000;
    public void run(){
        start(makeLottos(), makeLottoSystem());
    }
    private void start(Lottos lottos, LottoSystem lottoSystem){
        lottoService.revealLottery(lottos, lottoSystem);
    }
    private Lottos makeLottos(){
        return new Lottos(NumberOfLotto(getTotalMoneyByUserInput()));
    }
    private LottoSystem makeLottoSystem(){
        List<Integer> winningNumber = parser.parseWinningNumber(getWinningNumberByUserInput());
        int bonusNumber = parser.parseInteger(getBonusNumberByUserInput());
        return new LottoSystem(winningNumber, bonusNumber);
    }
    private String getTotalMoneyByUserInput(){
        LottoView.printAskInputMoney();
        String input = Console.readLine();
        return CheckValidateMoneyInput(input);
    }
    private String getWinningNumberByUserInput(){
        LottoView.printAskInputWinningNumber();
        String input = Console.readLine();
        return CheckValidateWinningNumberInput(input);
    }
    private String getBonusNumberByUserInput(){
        LottoView.printAskInputBonusNumber();
        String input = Console.readLine();
        return CheckValidateBonusNumberInput(input);
    }
    private String CheckValidateMoneyInput(String input){
        try {
            validate.CheckMoneyInput(input);
            return input;
        } catch (IllegalArgumentException e){
            LottoView.printException(e.getMessage());
            return  getTotalMoneyByUserInput();
        }
    }
    private String CheckValidateWinningNumberInput(String input){
        try{
            validate.CheckWinningNumber(input);
            return input;
        } catch (IllegalArgumentException e){
            LottoView.printException(e.getMessage());
            return getWinningNumberByUserInput();
        }
    }
    private String CheckValidateBonusNumberInput(String input){
        try{
            validate.CheckBonusNumber(input);
            return input;
        } catch (IllegalArgumentException e){
            LottoView.printException(e.getMessage());
            return getBonusNumberByUserInput();
        }
    }

    private int NumberOfLotto(String input){
        return parser.parseInteger(input)/LOTTO_PRICE;
    }
}

