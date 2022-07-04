package com.org.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import com.org.service.helper.AtmServiceHelper;

/**
 * @author Raj
 * @since 03-07-2022
 * 
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtmServiceHelperTest {

    @InjectMocks
    AtmServiceHelper atmServiceHelper;

    final int[] bankAmount = { 5, 5, 5, 5, 5 };
    final int[] bankValues = { 1000, 500, 100, 50, 20 };

    @Test
    public void subtractBankNumberCorrectly() {
        int[] usedBanks = { 2, 1, 0, 2, 4 };
        int[] balanceBanks = { 5, 5, 5, 5, 5 };
        int[] remainingBanks = { 3, 4, 5, 3, 1 };
        int[] result = atmServiceHelper.subtractBankAmt(balanceBanks, usedBanks);
        Assert.assertArrayEquals(remainingBanks, result);
    }

    @Test
    public void calculateTotalBalanceCorrectly() {
        int[] remainingBanks = { 1, 1, 1, 1, 1 };
        int expectedAmt = 1670;
        int result = atmServiceHelper.calCurrentTotalAmt(remainingBanks, bankValues);
        Assert.assertEquals(expectedAmt, result);
    }

    @Test
    public void findBanksCorrectly() {
        int[] currentBankAmt = new int[5];
        int[] balanceBankAmt = { 10, 10, 10, 10, 10 };
        List<int[]> expectedResult = new ArrayList<>();
        expectedResult.add(new int[] { 0, 0, 1, 0, 4 });
        expectedResult.add(new int[] { 0, 0, 0, 2, 4 });
        expectedResult.add(new int[] { 0, 0, 0, 0, 9 });

        List<int[]> result = atmServiceHelper.findBanks(180,
                currentBankAmt, balanceBankAmt, bankValues, 0);

        Assert.assertEquals(expectedResult.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            Assert.assertArrayEquals(result.get(i), expectedResult.get(i));
        }
    }

}
