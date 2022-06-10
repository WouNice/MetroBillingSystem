package org.example.common.enums;

/**
 * 站点权重值
 */
public enum Weight
{
    LOW(1),
    MID(2),
    TOP(3);

    private final int value;

    Weight(int value)
    {
        this.value = value;
    }

    public static boolean contains(int num)
    {
        for (Weight weight : Weight.values())
        {
            if (weight.value == num)
            {
                return true;
            }
        }
        return false;
    }
}
