package com.company.objects;

import com.github.npathai.hamcrestopt.OptionalMatchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class ComputerTest {

    @Test
    @DisplayName("빈 옵셔널 값을 만든다.")
    public void emptyOptional() {
        Optional<SoundCard> sc = Optional.empty();
        assertThat(sc, OptionalMatchers.isEmpty());
    }

    @Test
    @DisplayName("값이 존재하는 옵셔널을 만든다.")
    public void scOptional1() {
        SoundCard soundCard = new SoundCard();
        Optional<SoundCard> sc = Optional.of(soundCard);
        assertThat(sc, OptionalMatchers.isPresent());
    }

    @Disabled
    @Test
    @DisplayName("null을 Optional로 캡슐화 시도한다.")
    public void scOptional2() {
        SoundCard soundCard = null;
        Optional<SoundCard> sc = Optional.of(soundCard);
        assertThat(sc, OptionalMatchers.isEmpty());
    }

    @Test
    @DisplayName("null을 Optional로 캡슐화 시도한다.")
    public void scOptional3() {
        SoundCard soundCard = null;
        Optional<SoundCard> sc = Optional.ofNullable(soundCard);
        assertThat(sc, OptionalMatchers.isEmpty());
    }

    @Test
    @DisplayName("값이 존재하는 옵셔널일 경우 print한다.")
    public void scOptional4() {
        Optional<SoundCard> sc = Optional.of(new SoundCard());
        if (sc.isPresent()) {
            System.out.println(sc.get());
        }
        sc.ifPresent(System.out::println);
    }

    @Test
    @DisplayName("빈 옵셔널 객체라면 디폴트 값을 할당하도록 한다.")
    public void emptyOptional2() {
        //null인 객체
        Optional<SoundCard> emptySc = Optional.empty();

        //default 값으로 사용할 객체
        SoundCard defaultSc = new SoundCard();

        //emptySc가 null일 경우에는 defaultSc를 soundCard에 할당한다.
        SoundCard soundCard = emptySc.orElse(defaultSc);

        //최종적으로 할당된 값이 defaultSc인지 확인한다.
        assertThat(soundCard, is(defaultSc));
    }

    @Test
    @DisplayName("usb 시리얼 넘버가 AAA123일 경우에만 이를 반환하도록 한다.")
    public void usbOptional1() {
        //타겟인 문자열과 타겟이 아닌 문자열이다.
        String target = "AAA123";
        String notTarget = "BBB999";

        //각 시리얼 넘버를 가진 usb을 옵션 객체에 넣는다.
        Optional<USB> usb1 = Optional.of(new USB(target));
        Optional<USB> usb2 = Optional.of(new USB(notTarget));

        //옵션 객체를 담을 리스트
        List<Optional<USB>> usbList = new ArrayList<>();

        //각 usb의 시리얼 넘버가 타겟과 일치한 경우에만 비어있지 않은 옵션 객체가 들어간다.
        //즉, usb2는 타겟 넘버를 가지지 않았으므로 비어있는 옵션 객체가 들어갈 것이다.
        usbList.add(usb1.filter(usb -> usb.getSerialNumber().equals(target)));
        usbList.add(usb2.filter(usb -> usb.getSerialNumber().equals(target)));

        //리스트를 직렬화하여 usb가 존재하는 옵션 경우로만 필터링하고 배열로 바꿔 그 길이를 재었다. -> usb1만 존재할 것이므로 1
        assertThat(usbList.stream().filter(usb -> usb.isPresent()).toArray().length, equalTo(1));
        //리스트의 첫 번째 값은 usb1과 같을 것이다.
        //리스트의 두 번째 값은 Optional.empty() 즉, 비어있는 값 == null 일 것이다.
        assertThat(usbList.get(0).get(), equalTo(usb1.get()));
        assertThat(usbList.get(1).isEmpty(), equalTo(true));
    }


    @Test
    @DisplayName("필터링 테스트.")
    public void usbOptional2() {
        String target = "AAA123";
        USB usb = new USB(target);
        if (usb != null && usb.getSerialNumber().equals(target)) {
            System.out.println("기존의 if문을 이용한 방식");
        }

        Optional<USB> optionalUSB = Optional.of(new USB(target));
        optionalUSB.filter(optionalUsb -> optionalUsb.getSerialNumber().equals(target)).ifPresent(none -> System.out.println("optional을 통한 필터링"));
    }

    @Test
    @DisplayName("map 테스트")
    public void usbOptional3() {
        List<Optional<SoundCard>> optionalList = new ArrayList<>();

        Optional<SoundCard> emptySoundCard = Optional.empty();
        optionalList.add(emptySoundCard);

        Optional<SoundCard> haventUsbSoundCard = Optional.of(new SoundCard());
        haventUsbSoundCard.get().setUsb(Optional.empty());
        optionalList.add(haventUsbSoundCard);

        Optional<SoundCard> haveUsbSoundCard = Optional.of(new SoundCard());
        haveUsbSoundCard.get().setUsb(Optional.of(new USB("3.0")));
        optionalList.add(haveUsbSoundCard);

        for (int index = 0; index < optionalList.size(); ++index) {
            findSerialNumber3(optionalList.get(index), index);
        }
    }

    @Test
    @DisplayName("flatmap을 이용하여 연속적인 Optional 객체를 처리한다.")
    public void computerOptional1() {
        String target = "ABC123";

        SoundCard soundCard = new SoundCard();
        soundCard.setUsb(Optional.of(new USB(target)));
        Computer computer = Computer.builder().soundCard(soundCard).build();

        computer.toOptional().flatMap(Computer::getSoundCard)
                .flatMap(SoundCard::getUsb)
                .filter(usb -> usb.getSerialNumber().equals(target))
                .ifPresent(none -> System.out.println("is ok, target : " + target));

    }

    @Test
    @DisplayName("flatmap을 이용하여 연속적인 Optional 객체를 처리한다.")
    public void computerOptional2() {
        String target = "ABC123";

        SoundCard soundCard2 = SoundCard.builder().usb(null).build();
        SoundCard soundCard3 = SoundCard.builder().usb(new USB(target)).build();

        Computer computer1 = Computer.builder().soundCard(null).build();
        Computer computer2 = Computer.builder().soundCard(soundCard2).build();
        Computer computer3 = Computer.builder().soundCard(soundCard3).build();

        List<Computer> computerList = Arrays.asList(computer1, computer2, computer3);

        for (int index = 0; index < computerList.size(); ++index) {
            findTarget(computerList.get(index), index, target);
        }
    }

    private void findSerialNumber3(Optional<SoundCard> maybeSoundCard, int index) {
        maybeSoundCard.map(SoundCard::getUsb)
                .filter(usb -> usb.isPresent())
                .filter(usb -> usb.get().getSerialNumber().equals("3.0"))
                .ifPresent(none -> System.out.println("index : " + index));
    }

    private void findTarget(Computer computer, int index, String target) {
        computer.toOptional().flatMap(Computer::getSoundCard)
                .flatMap(SoundCard::getUsb)
                .filter(usb -> usb.getSerialNumber().equals(target))
                .ifPresent(none -> System.out.println("is ok, index : " + index + ", target : " + target));
    }

}

