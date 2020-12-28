package online.kingdomkeys.kingdomkeys.lib;

import static online.kingdomkeys.kingdomkeys.item.ModItems.*;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import online.kingdomkeys.kingdomkeys.util.Utils;

public class Lists {

    public static List<ResourceLocation> keybladeRecipes = new ArrayList<ResourceLocation>();
    public static List<ResourceLocation> itemRecipes = new ArrayList<ResourceLocation>();
    public static List<ResourceLocation> allRecipes = new ArrayList<ResourceLocation>();

    public static List<Item> Xemnas = new ArrayList<Item>();
    public static List<Item> Xigbar = new ArrayList<Item>();
    public static List<Item> Xaldin = new ArrayList<Item>();
    public static List<Item> Vexen = new ArrayList<Item>();
    public static List<Item> Lexaeus = new ArrayList<Item>();
    public static List<Item> Zexion = new ArrayList<Item>();
    public static List<Item> Saix = new ArrayList<Item>();
    public static List<Item> Axel = new ArrayList<Item>();
    public static List<Item> Demyx = new ArrayList<Item>();
    public static List<Item> Luxord = new ArrayList<Item>();
    public static List<Item> Marluxia = new ArrayList<Item>();
    public static List<Item> Larxene = new ArrayList<Item>();
    public static List<Item> Roxas = new ArrayList<>();

    public static List<Item> getListForMember(Utils.OrgMember member) {
        switch (member) {
            case AXEL: return Axel;
            case DEMYX: return Demyx;
            case LARXENE: return Larxene;
            case LEXAEUS: return Lexaeus;
            case LUXORD: return Luxord;
            case MARLUXIA: return Marluxia;
            case ROXAS: return Roxas;
            case SAIX: return Saix;
            case VEXEN: return Vexen;
            case XALDIN: return Xaldin;
            case XEMNAS: return Xemnas;
            case XIGBAR: return Xigbar;
            case ZEXION: return Zexion;
            case NONE: return null;
        }
        return null;
    }

    public static void init () {

        //Xemnas
     /*   Xemnas.add(malice.get());
        Xemnas.add(sanction.get());
        Xemnas.add(overlord.get());
        Xemnas.add(veneration.get());
        Xemnas.add(autocracy.get());
        Xemnas.add(conquest.get());
        Xemnas.add(terminus.get());
        Xemnas.add(judgement.get());
        Xemnas.add(discipline.get());
        Xemnas.add(aristocracy.get());
        Xemnas.add(superiority.get());
        Xemnas.add(aggression.get());
        Xemnas.add(fury.get());
        Xemnas.add(despair.get());
        Xemnas.add(triumph.get());
        Xemnas.add(ruination.get());
        Xemnas.add(domination.get());
        Xemnas.add(annihilation.get());
        Xemnas.add(tyrant.get());
        Xemnas.add(magnificence.get());
        Xemnas.add(infinity.get());
        Xemnas.add(interdiction.get());
        Xemnas.add(roundFan.get());
        Xemnas.add(absolute.get());

        //Xigbar
        Xigbar.add(standalone.get());
        Xigbar.add(killerbee.get());
        Xigbar.add(stingray.get());
        Xigbar.add(counterweight.get());
        Xigbar.add(precision.get());
        Xigbar.add(dualHead.get());
        Xigbar.add(bahamut.get());
        Xigbar.add(gullwing.get());
        Xigbar.add(blueFrame.get());
        Xigbar.add(starShell.get());
        Xigbar.add(sunrise.get());
        Xigbar.add(ignition.get());
        Xigbar.add(armstrong.get());
        Xigbar.add(hardBoiledHeat.get());
        Xigbar.add(diabloEye.get());
        Xigbar.add(doubleTap.get());
        Xigbar.add(stardust.get());
        Xigbar.add(energyMuzzle.get());
        Xigbar.add(crimeAndPunishment.get());
        Xigbar.add(cupidsArrow.get());
        Xigbar.add(finalWeapon.get());
        Xigbar.add(sharpshooter.get());
        Xigbar.add(dryer.get());
        Xigbar.add(trumpet.get());

        //Xaldin
        Xaldin.add(zephyr.get());
        Xaldin.add(moonglade.get());
        Xaldin.add(aer.get());
        Xaldin.add(nescience.get());
        Xaldin.add(brume.get());
        Xaldin.add(asura.get());
        Xaldin.add(crux.get());
        Xaldin.add(paladin.get());
        Xaldin.add(fellking.get());
        Xaldin.add(nightcloud.get());
        Xaldin.add(shimmer.get());
        Xaldin.add(vortex.get());
        Xaldin.add(scission.get());
        Xaldin.add(heavenfall.get());
        Xaldin.add(aether.get());
        Xaldin.add(mazzaroth.get());
        Xaldin.add(hegemon.get());
        Xaldin.add(foxfire.get());
        Xaldin.add(yaksha.get());
        Xaldin.add(cynosura.get());
        Xaldin.add(dragonreign.get());
        Xaldin.add(lindworm.get());
        Xaldin.add(broom.get());
        Xaldin.add(wyvern.get());

        //Vexen
        Vexen.add(testerZero.get());
        Vexen.add(productOne.get());
        Vexen.add(deepFreeze.get());
        Vexen.add(cryoliteShield.get());
        Vexen.add(falseTheory.get());
        Vexen.add(glacier.get());
        Vexen.add(absoluteZero.get());
        Vexen.add(gunz.get());
        Vexen.add(mindel.get());
        Vexen.add(snowslide.get());
        Vexen.add(iceberg.get());
        Vexen.add(inquisition.get());
        Vexen.add(scrutiny.get());
        Vexen.add(empiricism.get());
        Vexen.add(edification.get());
        Vexen.add(contrivance.get());
        Vexen.add(wurm.get());
        Vexen.add(subzero.get());
        Vexen.add(coldBlood.get());
        Vexen.add(diamondShield.get());
        Vexen.add(aegis.get());
        Vexen.add(frozenPride.get());
        Vexen.add(potLid.get());
        Vexen.add(snowman.get());

        //Lexaeus
        Lexaeus.add(reticence.get());
        Lexaeus.add(goliath.get());
        Lexaeus.add(copperRed.get());
        Lexaeus.add(daybreak.get());
        Lexaeus.add(colossus.get());
        Lexaeus.add(ursaMajor.get());
        Lexaeus.add(megacosm.get());
        Lexaeus.add(terrene.get());
        Lexaeus.add(fuligin.get());
        Lexaeus.add(hardWinter.get());
        Lexaeus.add(firefly.get());
        Lexaeus.add(harbinger.get());
        Lexaeus.add(redwood.get());
        Lexaeus.add(sequoia.get());
        Lexaeus.add(ironBlack.get());
        Lexaeus.add(earthshine.get());
        Lexaeus.add(octiron.get());
        Lexaeus.add(hyperion.get());
        Lexaeus.add(clarity.get());
        Lexaeus.add(oneThousandAndOneNights.get());
        Lexaeus.add(cardinalVirtue.get());
        Lexaeus.add(skysplitter.get());
        Lexaeus.add(bleepBloopBop.get());
        Lexaeus.add(monolith.get());

        //Zexion
        Zexion.add(blackPrimer.get());
        Zexion.add(whiteTome.get());
        Zexion.add(illicitResearch.get());
        Zexion.add(buriedSecrets.get());
        Zexion.add(arcaneCompendium.get());
        Zexion.add(dissentersNotes.get());
        Zexion.add(nefariousCodex.get());
        Zexion.add(mysticAlbum.get());
        Zexion.add(cursedManual.get());
        Zexion.add(tabooText.get());
        Zexion.add(eldritchEsoterica.get());
        Zexion.add(freakishBestiary.get());
        Zexion.add(madmansVita.get());
        Zexion.add(untitledWritings.get());
        Zexion.add(abandonedDogma.get());
        Zexion.add(atlasOfOmens.get());
        Zexion.add(revoltingScrapbook.get());
        Zexion.add(lostHeterodoxy.get());
        Zexion.add(otherworldlyTales.get());
        Zexion.add(indescribableLore.get());
        Zexion.add(radicalTreatise.get());
        Zexion.add(bookOfRetribution.get());
        Zexion.add(midnightSnack.get());
        Zexion.add(dearDiary.get());

        //Saix
        Saix.add(newMoon.get());
        Saix.add(werewolf.get());
        Saix.add(artemis.get());
        Saix.add(luminary.get());
        Saix.add(selene.get());
        Saix.add(moonrise.get());
        Saix.add(astrologia.get());
        Saix.add(crater.get());
        Saix.add(lunarPhase.get());
        Saix.add(crescent.get());
        Saix.add(gibbous.get());
        Saix.add(berserker.get());
        Saix.add(twilight.get());
        Saix.add(queenOfTheNight.get());
        Saix.add(balsamicMoon.get());
        Saix.add(orbit.get());
        Saix.add(lightYear.get());
        Saix.add(kingOfTheNight.get());
        Saix.add(moonset.get());
        Saix.add(horoscope.get());
        Saix.add(dichotomy.get());
        Saix.add(lunatic.get());
        Saix.add(justDesserts.get());
        Saix.add(bunnymoon.get());

        //Axel
        Axel.add(ashes.get());
        Axel.add(doldrums.get());
        Axel.add(delayedAction.get());
        Axel.add(diveBombers.get());
        Axel.add(combustion.get());
        Axel.add(moulinRouge.get());
        Axel.add(blazeOfGlory.get());
        Axel.add(prometheus.get());
        Axel.add(ifrit.get());
        Axel.add(magmaOcean.get());
        Axel.add(volcanics.get());
        Axel.add(inferno.get());
        Axel.add(sizzlingEdge.get());
        Axel.add(corona.get());
        Axel.add(ferrisWheel.get());
        Axel.add(burnout.get());
        Axel.add(omegaTrinity.get());
        Axel.add(outbreak.get());
        Axel.add(doubleEdge.get());
        Axel.add(wildfire.get());
        Axel.add(prominence.get());
        Axel.add(eternalFlames.get());
        Axel.add(pizzaCut.get());
        Axel.add(conformers.get());

        //Demyx
        Demyx.add(basicModel.get());
        Demyx.add(tuneUp.get());
        Demyx.add(quartet.get());
        Demyx.add(quintet.get());
        Demyx.add(overture.get());
        Demyx.add(oldHand.get());
        Demyx.add(daCapo.get());
        Demyx.add(powerChord.get());
        Demyx.add(fermata.get());
        Demyx.add(interlude.get());
        Demyx.add(serenade.get());
        Demyx.add(songbird.get());
        Demyx.add(riseToFame.get());
        Demyx.add(rockStar.get());
        Demyx.add(eightFinger.get());
        Demyx.add(concerto.get());
        Demyx.add(harmonics.get());
        Demyx.add(millionBucks.get());
        Demyx.add(fortissimo.get());
        Demyx.add(upToEleven.get());
        Demyx.add(sanctuary.get());
        Demyx.add(arpeggio.get());
        Demyx.add(princeOfAwesome.get());
        Demyx.add(afterSchool.get());

        //Luxord
        Luxord.add(theFool.get());
        Luxord.add(theMagician.get());
        Luxord.add(theStar.get());
        Luxord.add(theMoon.get());
        Luxord.add(justice.get());
        Luxord.add(theHierophant.get());
        Luxord.add(theWorld.get());
        Luxord.add(temperance.get());
        Luxord.add(theHighPriestess.get());
        Luxord.add(theTower.get());
        Luxord.add(theHangedMan.get());
        Luxord.add(death.get());
        Luxord.add(theHermit.get());
        Luxord.add(strength.get());
        Luxord.add(theLovers.get());
        Luxord.add(theChariot.get());
        Luxord.add(theSun.get());
        Luxord.add(theDevil.get());
        Luxord.add(theEmpress.get());
        Luxord.add(theEmperor.get());
        Luxord.add(theJoker.get());
        Luxord.add(fairGame.get());
        Luxord.add(finestFantasy13.get());
        Luxord.add(highRollersSecret.get());

        //Marluxia
        Marluxia.add(fickleErica.get());
        Marluxia.add(jiltedAnemone.get());
        Marluxia.add(proudAmaryllis.get());
        Marluxia.add(madSafflower.get());
        Marluxia.add(poorMelissa.get());
        Marluxia.add(tragicAllium.get());
        Marluxia.add(mournfulCineria.get());
        Marluxia.add(pseudoSilene.get());
        Marluxia.add(faithlessDigitalis.get());
        Marluxia.add(grimMuscari.get());
        Marluxia.add(docileVallota.get());
        Marluxia.add(quietBelladonna.get());
        Marluxia.add(partingIpheion.get());
        Marluxia.add(loftyGerbera.get());
        Marluxia.add(gallantAchillea.get());
        Marluxia.add(noblePeony.get());
        Marluxia.add(fearsomeAnise.get());
        Marluxia.add(vindictiveThistle.get());
        Marluxia.add(fairHelianthus.get());
        Marluxia.add(solemnMagnolia.get());
        Marluxia.add(hallowedLotus.get());
        Marluxia.add(gracefulDahlia.get());
        Marluxia.add(stirringLadle.get());
        Marluxia.add(daintyBellflowers.get());

        //Larxene
        Larxene.add(trancheuse.get());
        Larxene.add(orage.get());
        Larxene.add(tourbillon.get());
        Larxene.add(tempete.get());
        Larxene.add(carmin.get());
        Larxene.add(meteore.get());
        Larxene.add(etoile.get());
        Larxene.add(irregulier.get());
        Larxene.add(dissonance.get());
        Larxene.add(eruption.get());
        Larxene.add(soleilCouchant.get());
        Larxene.add(indigo.get());
        Larxene.add(vague.get());
        Larxene.add(deluge.get());
        Larxene.add(rafale.get());
        Larxene.add(typhon.get());
        Larxene.add(extirpeur.get());
        Larxene.add(croixDuSud.get());
        Larxene.add(lumineuse.get());
        Larxene.add(clairDeLune.get());
        Larxene.add(volDeNuit.get());
        Larxene.add(foudre.get());
        Larxene.add(demoiselle.get());
        Larxene.add(ampoule.get());*/

        Roxas.add(kingdomKey.get());
        Roxas.add(missingAche.get());
        Roxas.add(ominousBlight.get());
        Roxas.add(abaddonPlasma.get());
        Roxas.add(painOfSolitude.get());
        Roxas.add(signOfInnocence.get());
        Roxas.add(crownOfGuilt.get());
        Roxas.add(abyssalTide.get());
        Roxas.add(leviathan.get());
        Roxas.add(trueLightsFlight.get());
        Roxas.add(rejectionOfFate.get());
        Roxas.add(midnightRoar.get());
        Roxas.add(glimpseOfDarkness.get());
        Roxas.add(totalEclipse.get());
        Roxas.add(silentDirge.get());
        Roxas.add(lunarEclipse.get());
        Roxas.add(darkerThanDark.get());
        Roxas.add(astralBlast.get());
        Roxas.add(maverickFlare.get());
        Roxas.add(twilightBlaze.get());
        Roxas.add(omegaWeapon.get());
        Roxas.add(oathkeeper.get());
        Roxas.add(twoBecomeOne.get());
        Roxas.add(oblivion.get());
        Roxas.add(umbrella.get());
        Roxas.add(aubade.get());
        Roxas.add(woodenStick.get());

        //Keyblades
       /* keybladeRecipes.add(abaddonPlasma.get().getRegistryName());
        keybladeRecipes.add(abyssalTide.get().getRegistryName());
        keybladeRecipes.add(allForOne.get().getRegistryName());
        keybladeRecipes.add(invisKeyblade.get().getRegistryName());
        keybladeRecipes.add(astralBlast.get().getRegistryName());
        keybladeRecipes.add(aubade.get().getRegistryName());
        keybladeRecipes.add(bondOfFlame.get().getRegistryName());
        keybladeRecipes.add(brightcrest.get().getRegistryName());
        keybladeRecipes.add(chaosRipper.get().getRegistryName());
        keybladeRecipes.add(circleOfLife.get().getRegistryName());
        keybladeRecipes.add(counterpoint.get().getRegistryName());
        keybladeRecipes.add(crabclaw.get().getRegistryName());
        keybladeRecipes.add(crownOfGuilt.get().getRegistryName());
        keybladeRecipes.add(darkerThanDark.get().getRegistryName());
        keybladeRecipes.add(darkgnaw.get().getRegistryName());
        keybladeRecipes.add(decisivePumpkin.get().getRegistryName());
        keybladeRecipes.add(destinysEmbrace.get().getRegistryName());
        keybladeRecipes.add(diamondDust.get().getRegistryName());
        keybladeRecipes.add(divewing.get().getRegistryName());
        keybladeRecipes.add(divineRose.get().getRegistryName());
        //recipes.add(dreamShield.get().getRegistryName());
        //recipes.add(dreamStaff.get().getRegistryName());
        //recipes.add(dreamSword.get().getRegistryName());
        keybladeRecipes.add(dualDisc.get().getRegistryName());
        keybladeRecipes.add(earthshaker.get().getRegistryName());
        keybladeRecipes.add(endOfPain.get().getRegistryName());
        keybladeRecipes.add(endsOfTheEarth.get().getRegistryName());
        keybladeRecipes.add(fairyHarp.get().getRegistryName());
        keybladeRecipes.add(fairyStars.get().getRegistryName());
        keybladeRecipes.add(fatalCrest.get().getRegistryName());
        keybladeRecipes.add(fenrir.get().getRegistryName());
        keybladeRecipes.add(ferrisGear.get().getRegistryName());
        keybladeRecipes.add(followTheWind.get().getRegistryName());
        keybladeRecipes.add(frolicFlame.get().getRegistryName());
        keybladeRecipes.add(glimpseOfDarkness.get().getRegistryName());
        keybladeRecipes.add(guardianBell.get().getRegistryName());
        keybladeRecipes.add(guardianSoul.get().getRegistryName());
        keybladeRecipes.add(gullWing.get().getRegistryName());
        keybladeRecipes.add(herosCrest.get().getRegistryName());
        keybladeRecipes.add(hiddenDragon.get().getRegistryName());
        keybladeRecipes.add(hyperdrive.get().getRegistryName());
        keybladeRecipes.add(incompleteKiblade.get().getRegistryName());
        keybladeRecipes.add(jungleKing.get().getRegistryName());
        keybladeRecipes.add(keybladeOfPeoplesHearts.get().getRegistryName());
        keybladeRecipes.add(kiblade.get().getRegistryName());
        keybladeRecipes.add(kingdomKey.get().getRegistryName());
        keybladeRecipes.add(kingdomKeyD.get().getRegistryName());
        keybladeRecipes.add(knockoutPunch.get().getRegistryName());
        keybladeRecipes.add(ladyLuck.get().getRegistryName());
        keybladeRecipes.add(flameLiberator.get().getRegistryName());
        keybladeRecipes.add(gulasKeyblade.get().getRegistryName());
        keybladeRecipes.add(leviathan.get().getRegistryName());
        keybladeRecipes.add(lionheart.get().getRegistryName());
        keybladeRecipes.add(lostMemory.get().getRegistryName());
        keybladeRecipes.add(lunarEclipse.get().getRegistryName());
        keybladeRecipes.add(markOfAHero.get().getRegistryName());
        keybladeRecipes.add(mastersDefender.get().getRegistryName());
        keybladeRecipes.add(maverickFlare.get().getRegistryName());
        keybladeRecipes.add(metalChocobo.get().getRegistryName());
        keybladeRecipes.add(midnightRoar.get().getRegistryName());
        keybladeRecipes.add(mirageSplit.get().getRegistryName());
        keybladeRecipes.add(missingAche.get().getRegistryName());
        keybladeRecipes.add(monochrome.get().getRegistryName());
        keybladeRecipes.add(moogleOGlory.get().getRegistryName());
        keybladeRecipes.add(mysteriousAbyss.get().getRegistryName());
        keybladeRecipes.add(nightmaresEnd.get().getRegistryName());
        keybladeRecipes.add(nightmaresEndAndMirageSplit.get().getRegistryName());
        keybladeRecipes.add(noName.get().getRegistryName());
        keybladeRecipes.add(noNameBBS.get().getRegistryName());
        keybladeRecipes.add(oathkeeper.get().getRegistryName());
        keybladeRecipes.add(oblivion.get().getRegistryName());
        keybladeRecipes.add(oceansRage.get().getRegistryName());
        keybladeRecipes.add(olympia.get().getRegistryName());
        keybladeRecipes.add(omegaWeapon.get().getRegistryName());
        keybladeRecipes.add(ominousBlight.get().getRegistryName());
        keybladeRecipes.add(oneWingedAngel.get().getRegistryName());
        keybladeRecipes.add(painOfSolitude.get().getRegistryName());
        keybladeRecipes.add(photonDebugger.get().getRegistryName());
        keybladeRecipes.add(pixiePetal.get().getRegistryName());
        keybladeRecipes.add(pumpkinhead.get().getRegistryName());
        keybladeRecipes.add(rainfell.get().getRegistryName());
        keybladeRecipes.add(rejectionOfFate.get().getRegistryName());
        keybladeRecipes.add(royalRadiance.get().getRegistryName());
        keybladeRecipes.add(rumblingRose.get().getRegistryName());
        keybladeRecipes.add(shootingStar.get().getRegistryName());
        keybladeRecipes.add(signOfInnocence.get().getRegistryName());
        keybladeRecipes.add(silentDirge.get().getRegistryName());
        keybladeRecipes.add(skullNoise.get().getRegistryName());
        keybladeRecipes.add(sleepingLion.get().getRegistryName());
        keybladeRecipes.add(soulEater.get().getRegistryName());
        keybladeRecipes.add(spellbinder.get().getRegistryName());
        keybladeRecipes.add(starlight.get().getRegistryName());
        keybladeRecipes.add(starSeeker.get().getRegistryName());
        keybladeRecipes.add(stormfall.get().getRegistryName());
        keybladeRecipes.add(strokeOfMidnight.get().getRegistryName());
        keybladeRecipes.add(sweetDreams.get().getRegistryName());
        keybladeRecipes.add(sweetMemories.get().getRegistryName());
        keybladeRecipes.add(sweetstack.get().getRegistryName());
        keybladeRecipes.add(threeWishes.get().getRegistryName());
        keybladeRecipes.add(totalEclipse.get().getRegistryName());
        keybladeRecipes.add(treasureTrove.get().getRegistryName());
        keybladeRecipes.add(trueLightsFlight.get().getRegistryName());
        keybladeRecipes.add(twilightBlaze.get().getRegistryName());
        keybladeRecipes.add(twoBecomeOne.get().getRegistryName());
        keybladeRecipes.add(ultimaWeaponBBS.get().getRegistryName());
        keybladeRecipes.add(ultimaWeaponDDD.get().getRegistryName());
        keybladeRecipes.add(ultimaWeaponKH1.get().getRegistryName());
        keybladeRecipes.add(ultimaWeaponKH2.get().getRegistryName());
        keybladeRecipes.add(ultimaWeaponKH3.get().getRegistryName());
        keybladeRecipes.add(umbrella.get().getRegistryName());
        keybladeRecipes.add(unbound.get().getRegistryName());
        keybladeRecipes.add(irasKeyblade.get().getRegistryName());
        keybladeRecipes.add(acedsKeyblade.get().getRegistryName());
        keybladeRecipes.add(victoryLine.get().getRegistryName());
        keybladeRecipes.add(voidGear.get().getRegistryName());
        keybladeRecipes.add(voidGearRemnant.get().getRegistryName());
        keybladeRecipes.add(avasKeyblade.get().getRegistryName());
        keybladeRecipes.add(waytotheDawn.get().getRegistryName());
        keybladeRecipes.add(waywardWind.get().getRegistryName());
        keybladeRecipes.add(winnersProof.get().getRegistryName());
        keybladeRecipes.add(wishingLamp.get().getRegistryName());
        keybladeRecipes.add(wishingStar.get().getRegistryName());
        keybladeRecipes.add(youngXehanortsKeyblade.get().getRegistryName());
        keybladeRecipes.add(zeroOne.get().getRegistryName());
        
        //Items
        itemRecipes.add(mythril_crystal.get().getRegistryName());
        itemRecipes.add(mythril_gem.get().getRegistryName());
        itemRecipes.add(mythril_stone.get().getRegistryName());
        itemRecipes.add(mythril_shard.get().getRegistryName());
        itemRecipes.add(Items.DIAMOND.getRegistryName());
        
       /* TODO for(String keyblade : MainConfig.items.bannedKeyblades) {
        	if(recipes.indexOf("item."+keyblade) >= 0)
        		recipes.remove(recipes.indexOf("item."+keyblade));
        }*/
        
       // allRecipes.addAll(keybladeRecipes);
        //allRecipes.addAll(itemRecipes);
    }

}
