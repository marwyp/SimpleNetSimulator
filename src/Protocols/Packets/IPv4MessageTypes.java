package Protocols.Packets;

public enum IPv4MessageTypes {
    is_valid,
    // ip address
    is_not_dotted_format,
    octet_value_must_be_int,
    octet_value_is_too_big,
    octet_value_must_be_positive,
    // net mask
    mask_value_must_be_int,
    mask_value_is_to_big,
    mask_value_must_be_positive,
    mask_value_is_incorrect,
    // ip address on interface
    is_net_address,
    is_broadcast_address,
    overlaps,
    // net mask on interface
    mask_is_over_30,

}
