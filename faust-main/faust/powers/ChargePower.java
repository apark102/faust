package faust.powers;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import faust.BasePower;

import static faust.BasicMod.makeID;

public class ChargePower {
    public static final String POWER_ID = makeID("Charge");
    private static final AbstractPower.PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public ChargePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
    @Override
    public void updateDescription() {
        this.description = "Stackable energy. Can be consumed by certain cards for various effects.";
    }
}
