pub fn sol1 () {
    let input = include_str!("../inputs/day03.txt");

    let output = input
        .split_terminator('\n')
        .fold(0, |mut acc, x| {
            let sack_size = x.len();                        // getting the length of the line
            let (left, right) = x.split_at(sack_size / 2);  // splitting the line into compartments

            let left_contents: u64 = left                   // bits in this u64 represent which letters are present in left compartment
                .chars()
                .fold(0, |acc, y| {
                    let shift = match y as u64 {
                        65..=90 => (y as u64) - 65 + 26,    // capital letters are 26-51
                        _ => (y as u64) - 97,               // lower-case letters ar 0-25
                    };

                    acc | 0b1u64 << shift                   // setting the Nth bit (a..zA..Z corresspond to the 0th..51st bits)
                });

            for letter in right.chars() {
                let shift = match letter as u64 {
                    65..=90 => (letter as u64) - 65 + 26,
                    _ => (letter as u64) - 97,
                };

                if ((left_contents & (0b1u64 << shift)) >> shift) != 0 {        // AND-ing the shifted value for current letter with the u64 above
                    acc += shift + 1;
                    break;
                }
            }

            //println!("{left_contents:#064b}");              // for debugging

            acc
        });

    println!("day03 sol1: {}", output);
}

pub fn sol2() {
    let input = include_str!("../inputs/day03.txt");

    let output = input
        .to_string()
        .split_terminator('\n')
        .collect::<Vec<_>>()
        .chunks(3)                  // splitting lines into groups of 3
        .fold(0, |mut acc1, x|{     // iterating over all of the groups
            let badge = x.iter()    // iterating over elves in each group
                         .fold(!0b0u64, |mut acc2, y| {                 // want to keep all items in first sack, so init acc is max int
                             acc2 = y.chars()                           // getting a new binary number representing the items similar between curr and prev sacks
                                     .fold(0b0u64, |mut acc3, z| {
                                         let shift = match z as u64 {
                                             65..=90 => (z as u64) - 65 + 26,
                                             _ => (z as u64) - 97,
                                         };

                                         if (acc2 & (0b1u64 << shift)) >> shift != 0 {   // checking if the curr char is in the previous sack
                                             acc3 |= 0b1u64 << shift;
                                         }

                                         acc3
                                     });
                             acc2
                         })
                         .ilog2();

            acc1 += badge + 1;

            acc1
        });

    println!("day03 sol2: {}", output);
}