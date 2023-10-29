package racingcar.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import racingcar.model.Car;
import racingcar.validator.CarValidator;
import racingcar.validator.InputValidator;
import racingcar.view.InputView;

public class CarGame {
    private InputView inputView;

    public CarGame() {
        inputView = new InputView();
    }

    public void start() {
        String inputCarName = getCarNamesInput();
        List<Car> carList = getCarList(splitCarNames(inputCarName));
        String inputTryNumber = getTryNumberInput();
    }

    private String getCarNamesInput() {
        inputView.printInputCarName();
        String input = Console.readLine().trim();
        return checkCarNamesInput(input);
    }

    private String checkCarNamesInput(String input) {
        try {
            InputValidator.isNullOrIsEmpty(input);
            InputValidator.includeNumberOrSymbol(input);
            InputValidator.includeSpace(input);
            List<String> carNameList = splitCarNames(input);
            CarValidator.isOnlyLowerCase(carNameList);
            CarValidator.checkCarNameLength(carNameList);
            CarValidator.checkDuplicateCarName(carNameList);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            getCarNamesInput();
        }
        return input;
    }

    private List<String> splitCarNames(String input) {
        String[] nameArray = input.split(",");
        List<String> nameList = new ArrayList<>(Arrays.stream(nameArray).toList());
        return nameList;
    }

    private List<Car> getCarList(List<String> carNameList) {
        List<Car> carList = new ArrayList<>();
        for (String carName : carNameList) {
            carList.add(new Car(carName, 0));
        }
        return carList;
    }

    private String getTryNumberInput() {
        inputView.printInputTryNumber();
        String input = Console.readLine().trim();
        return checkTryNumberInput(input);
    }

    private String checkTryNumberInput(String input) {
        try {
            InputValidator.isNullOrIsEmpty(input);
            InputValidator.isNotDigit(input);
            return input;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getTryNumberInput();
        }
    }
}
